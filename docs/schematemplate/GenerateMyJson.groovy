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
        if(it.isJson > 0){
            new File(dir, it.type + ".java").withPrintWriter { out -> generateField(out, it) }
            //new File(dir.toString()+"/converter").mkdirs()
            //new File(dir.toString()+"/converter", it.type2 + "Converter.java").withPrintWriter { out -> generateFieldConverter(out, it) }
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
    out.println "import lombok.AllArgsConstructor;"
    out.println "import lombok.Builder;"
    out.println "import lombok.Getter;"
    out.println "import lombok.Setter;"
    out.println "import lombok.NoArgsConstructor;"
    out.println "import java.lang.reflect.Field;"
    out.println "import java.util.Objects;"
    out.println ""

    out.println "/**"
    out.println " * @author "
    out.println " */"
    out.println "@Getter"
    out.println "@Setter"
    out.println "@NoArgsConstructor"
    out.println "@AllArgsConstructor"
    out.println "@Builder"
    out.println "public class ${field.type} {"
    out.println "  private String sample;"
    out.println ""
//    out.println ""
//    out.println ""
//    out.println "  @Override"
//    out.println "  public boolean equals(Object o) {"
//    out.println "    if (this == o) { return true; }"
//    out.println "    if(o == null) { return false; }"
//    out.println ""
//    out.println "    Class<?> thisClass = getClass();"
//    out.println "    Class<?> destClass = o.getClass();"
//    out.println ""
//    out.println "    if (thisClass != destClass) { return false; }"
//    out.println ""
//    out.println "    for (Field field : thisClass.getDeclaredFields()) {"
//    out.println "      try {"
//    out.println "        if (!Objects.equals(field.get(this), destClass.getDeclaredField(field.getName()).get(o))) {"
//    out.println "          return false;"
//    out.println "        }"
//    out.println "      } catch (IllegalAccessException | NoSuchFieldException e) {"
//    out.println "        e.printStackTrace();"
//    out.println "      }"
//    out.println "    }"
//    out.println ""
//    out.println "    return true;"
//    out.println "  }"
    out.println "}"
}

def generateFieldConverter(out, field) {

    out.println """/*
 * Allgoyou Inc., Software License, Version 1.0
 *
 * Copyright (c) 2021. Alltoyou Inc., All rights reserved.
 *
 * DON'T COPY OR REDISTRIBUTE THIS SOURCE CODE WITHOUT PERMISSION.
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <<Allgoyou Inc.>> OR ITS
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 * For more information on this product, please see www.allgoyou.com
 */
    """

    out.println "package ${packageName}.converter;"
    out.println ""
    out.println "import ${packageRootName}.core.utils.JsonConverterAbstract;"
    out.println "import ${packageName}.${field.type};"
    out.println "import javax.persistence.AttributeConverter;"
    out.println "import javax.persistence.Converter;"
    out.println ""

    out.println "/**"
    out.println " * @author "
    out.println " */"
    out.println "@Converter"
    out.println "public class ${field.type2}Converter extends JsonConverterAbstract<${field.type}> implements AttributeConverter<${field.type}, String> {"
    out.println "  private static final ${field.type} ${field.type3} = new ${field.type}();"
    out.println "  "
    out.println "  @Override"
    out.println "  public ${field.type} getInstance() {"
    out.println "    return ${field.type2}Converter.${field.type3};"
    out.println "  }"
    out.println "}"
}

def calcFields(table) {
    DasUtil.getColumns(table).reduce([]) { fields, col ->
        def spec = Case.LOWER.apply(col.getDataType().getSpecification())
        def typeStr = typeMapping.find { p, t -> p.matcher(spec).find() }.value
        def colName =  javaName(col.getName(), false)
        def colEndTypeIndex = colName.toString().length()-1
        def colFirstUpperName = colName.toString().substring(0,1).toUpperCase()
        def colFirstLowerName = colName.toString().substring(0,1).toLowerCase()
        def colEndFix = colName.toString().substring(colEndTypeIndex)
        def colJsonName = colName.toString().substring(1, colEndTypeIndex)
        def isJson = colEndFix == "\$"? 1 : 0
        def typeStr2 = typeStr
        def typeStr3 = typeStr

        if(isJson > 0){
            typeStr = colFirstUpperName+colJsonName+"Json"
            typeStr2 = colFirstUpperName+colJsonName
            typeStr3 = colFirstLowerName+colJsonName+"Json"
        }

        def comm = [
                name : colName,
                type : typeStr,
                type2 : typeStr2,
                type3 : typeStr3,
                isJson : isJson,
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

