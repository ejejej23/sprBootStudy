import com.intellij.database.model.DasTable
import com.intellij.database.util.Case
import com.intellij.database.util.DasUtil

/*
 * Available context bindings:
 *   SELECTION   Iterable<DasObject>
 *   PROJECT     project
 *   FILES       files helper
 */
packageRootName = "com.naon"
packageName = ""
typeMapping = [
        (~/(?i)smallint/)                 : "Short",
        (~/(?i)bigint/)                   : "Long",
        (~/(?i)int/)                      : "Integer",
        (~/(?i)float|double|decimal|real|numeric/): "Double",
        (~/(?i)datetime|timestamp/)       : "LocalDateTime",
        (~/(?i)date/)                     : "LocalDate",
        (~/(?i)time/)                     : "LocalTime",
        (~/(?i)boolean/)                  : "Boolean",
        (~/(?i)/)                         : "String"
]


FILES.chooseDirectoryAndSave("Choose directory", "Choose where to store generated files") { dir ->
    SELECTION.filter { it instanceof DasTable }.each { generate(it, dir) }
}

def generate(table, dir) {
    def fields = calcFields(table)

    packageName = getPackageName(dir)

    fields.eachWithIndex{it,index->
        if(it.isEnum > 0){
            new File(dir, it.type + ".java").withPrintWriter { out -> generateField(out, it) }
        }
    }
}

def getPackageName(dir) {
    return dir.toString().replaceAll("\\\\", ".").replaceAll("/", ".").replaceAll("^.*src(\\.main\\.java\\.)?", "")
}

def getPackageParentName() {
    def dr = packageName.toString()
    return dr.substring(0, dr.lastIndexOf("."))
}

def generateField(out, field) {

    out.println """/*
 * Naonsoft Inc., Software License, Version 8.0
 * Copyright (c) 2007-2021 Naonsoft Inc.,
 * All rights reserved.
 *
 * DON'T COPY OR REDISTRIBUTE THIS SOURCE CODE WITHOUT PERMISSION.
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <<Naonsoft Inc.>> OR ITS
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 * For more information on this product, please see<<www.naonsoft.com>>
 */
    """

    out.println "package $packageName;"
    out.println ""
    out.println "import ${packageRootName}.core.utils.EnumType;"
    out.println "import com.fasterxml.jackson.annotation.JsonCreator;"
    out.println "import java.util.Map;"
    out.println "import java.util.stream.Stream;"
    out.println "import lombok.Getter;"
    out.println "import lombok.RequiredArgsConstructor;"
    out.println "import static java.util.stream.Collectors.toMap;"
    out.println ""

    out.println "/**"
    out.println " * @author "
    out.println " */"
    out.println "@Getter"
    out.println "@RequiredArgsConstructor"
    out.println "public enum ${field.type} implements EnumType {"
    out.println "  A(\"EXAMPLE1\"),"
    out.println "  F(\"EXAMPLE2\");"
    out.println "  "
    out.println "  private static final Map<String, ${field.type}> nameToEnum ="
    out.println "      Stream.of(values()).collect(toMap(Object::toString, e -> e));"
    out.println "  "
    out.println "  private final String text;"
    out.println "  "
    out.println "  @JsonCreator"
    out.println "  public static ${field.type} enumfromName(String name) { return nameToEnum.get(name); }"
    out.println "  "
    out.println "  @Override"
    out.println "  public String getId() { return this.name(); }"
    out.println "  "
    out.println "}"
}

def calcFields(table) {
    DasUtil.getColumns(table).reduce([]) { fields, col ->
        def spec = Case.LOWER.apply(col.getDataType().getSpecification())
        def typeStr = typeMapping.find { p, t -> p.matcher(spec).find() }.value
        def colName =  javaName(col.getName(), false)
        def colTypeIndex = colName.toString().length()-3
        def colStatTypeIndex = colName.toString().length()-4
        def colPostFix = colName.toString().substring(colTypeIndex)
        def colStatPostFix = colName.toString().substring(colStatTypeIndex)
        def colFirstUpperName = colName.toString().substring(0,1).toUpperCase()
        def colRestName = colName.toString().substring(1)
        def isEnum = colPostFix == "Typ" || colStatPostFix == "Stat"? 1 : 0

        def comm = [
                name : colName,
                type : isEnum > 0? colFirstUpperName+colRestName+"e" : typeStr,
                isEnum : isEnum,
                colName : col.getName(),
                comment: col.getComment(),
                annos: "@Column(name = \"" + col.getName() + "\")"]

        fields += [comm]
    }
}

def javaName(str, capitalize) {
    def s = com.intellij.psi.codeStyle.NameUtil.splitNameIntoWords(str)
            .collect { Case.LOWER.apply(it).capitalize() }
            .join("")
            .replaceAll(/[^\p{javaJavaIdentifierPart}[_]]/, "_")
    capitalize || s.length() == 1? s : Case.LOWER.apply(s[0]) + s[1..-1]
}

