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
packageParentName = ""
tableName = ""
entityName = ""
modelName = ""
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
    tableName = table.getName()
    entityName = tableName.toString()
    entityName = entityName.substring(entityName.indexOf("_")+1)
    modelName = entityName
    entityName = javaName(entityName, true)
    lowerEntityName = javaName(entityName, false)
    def className = javaName(table.getName(), true)
    def fields = calcFields(table)
    packageName = getPackageName(dir)
    packageParentName = getPackageParentName()
    new File(dir, entityName + "Dto.java").withPrintWriter { out -> generate(out, className, fields) }
}

def getPackageName(dir) {
    return dir.toString().replaceAll("\\\\", ".").replaceAll("/", ".").replaceAll("^.*src(\\.main\\.java\\.)?", "")
}

def getPackageParentName() {
    def dr = packageName.toString()
    return dr.substring(0, dr.lastIndexOf("."))
}

def generate(out, className, fields) {
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
    out.println "import ${packageRootName}.core.utils.IdGenerator;"
    out.println "import ${packageParentName}.domain.${entityName};"
    out.println "import javax.validation.constraints.NotEmpty;"
    out.println "import lombok.AccessLevel;"
    out.println "import lombok.Builder;"
    out.println "import lombok.Getter;"
    out.println "import lombok.AllArgsConstructor;"
    out.println "import lombok.NoArgsConstructor;"
    out.println "import lombok.Setter;"
    out.println "import com.querydsl.core.annotations.QueryProjection;"

    Set types = new HashSet()

    fields.each() {
        types.add(it.type)

        if(it.isEnum > 0){
            out.println "import ${packageParentName}.enums.${it.type};"
        }

        if(it.isJson > 0){
            out.println "import ${packageParentName}.json.${it.type};"
        }

    }

    if (types.contains("LocalDateTime")) {
        out.println "import java.time.LocalDateTime;"
    }

    if (types.contains("LocalDate")) {
        out.println "import java.time.LocalDate;"
    }

    if (types.contains("LocalTime")) {
        out.println "import java.time.LocalTime;"
    }

    out.println ""
    out.println ""

    out.println "/**"
    out.println " * @author "
    out.println " */"
    out.println "public class ${entityName}Dto {"

    def fieldRequest = "";
    def fieldResponse = "";
    def assignToEntity = "                  ";

    def assignResponse = ""
    def paramRequest = "";
    def paramResponse = "";
    def assignBuilder = "                  ";

    fields.eachWithIndex{it,index->
        if(index > 0 && it.name != "regDt" && it.name != "modDt") {
            fieldRequest += "      private ${it.type} ${it.name};"
            if (it.comment != null)  fieldRequest += "//${it.comment}"
            fieldRequest += "\r\n"

            if (index == 1) {
                paramRequest += "${it.type} ${it.name}"
            } else {
                paramRequest += " ,${it.type} ${it.name}"
            }
            assignToEntity += ".${it.name}(${it.name})"
            if ((index % 4) == 0) assignToEntity += "\r\n"
        }
        fieldResponse += "      private ${it.type} ${it.name};"
        if (it.comment != null)  fieldResponse += "//${it.comment}"
        fieldResponse += "\r\n"

        if (index == 0) {
            paramResponse += "${it.type} ${it.name}"
        } else {
            paramResponse += " ,${it.type} ${it.name}"
        }
        assignResponse += "             this.${it.name} = ${it.name};\r\n"
        assignBuilder += ".${it.name}(${lowerEntityName}.get${javaName(it.name, true)}())"
        if ((index % 4) == 0) assignBuilder += "\r\n"

    }

    out.println "   @Getter"
    out.println "   @Builder"
    out.println "   @NoArgsConstructor(access = AccessLevel.PACKAGE)"
    out.println "   @AllArgsConstructor(access = AccessLevel.PACKAGE)"
    out.println "   public static class Create {"
    out.println fieldRequest
    out.println "       public ${entityName} toEntity() {"
    out.println "           return ${entityName}.builder()"
    out.println assignToEntity
    out.println "           .build();"
    out.println "       }"
    out.println "   }"
    out.println ""
    out.println "   @Getter"
    out.println "   @Builder"
    out.println "   @NoArgsConstructor(access = AccessLevel.PACKAGE)"
    out.println "   @AllArgsConstructor(access = AccessLevel.PACKAGE)"
    out.println "   public static class Update {"
    out.println fieldRequest
//    out.println "       public ${entityName} toEntity() {"
//    out.println "           return ${entityName}.builder()"
//    out.println assignToEntity
//    out.println "           .build();"
//    out.println "       }"
    out.println "   }"
    out.println ""
    out.println "   @Getter @Setter"
    out.println "   @Builder"
    out.println "   @NoArgsConstructor(access = AccessLevel.PACKAGE)"
    out.println "   //@AllArgsConstructor(access = AccessLevel.PACKAGE)"
    out.println "   public static class Search {"
    out.println "   }"
    out.println ""
    out.println "   @Getter"
    out.println "   @Builder"
    out.println "   @NoArgsConstructor(access = AccessLevel.PACKAGE)"
    out.println "   @AllArgsConstructor(access = AccessLevel.PACKAGE)"
    out.println "   public static class Response {"
    out.println fieldResponse
    out.println ""
    out.println "       public static Response of(final ${entityName} ${lowerEntityName}) {"
    out.println "           return Response.builder()"
    out.println assignBuilder
    out.println "           .build();"
    out.println "       }"
    out.println "   }"

    out.println "   @Getter"
    out.println "   @NoArgsConstructor(access = AccessLevel.PACKAGE)"
    out.println "   public static class ResponseList {"
    out.println fieldResponse
    out.println ""
    out.println "       @Builder"
    out.println "       @QueryProjection"
    out.println "       public ResponseList(${paramResponse}) {"
    out.println  assignResponse
    out.println "       }"
    out.println "   }"

    out.println "}"
}

def calcFields(table) {
    DasUtil.getColumns(table).reduce([]) { fields, col ->
        def spec = Case.LOWER.apply(col.getDataType().getSpecification())
        def typeStr = typeMapping.find { p, t -> p.matcher(spec).find() }.value
        def colName =  javaName(col.getName(), false)
        def colTypeIndex = colName.toString().length()-3
        def colStatTypeIndex = colName.toString().length()-4
        def colEndTypeIndex = colName.toString().length()-1
        def colPostFix = colName.toString().substring(colTypeIndex)
        def colStatPostFix = colName.toString().substring(colStatTypeIndex)
        def colFirstUpperName = colName.toString().substring(0,1).toUpperCase()
        def colRestName = colName.toString().substring(1)

        def colEndFix = colName.toString().substring(colEndTypeIndex)
        def colJsonName = colName.toString().substring(1, colEndTypeIndex)
        def isEnum = colPostFix == "Typ" || colStatPostFix == "Stat"? 1 : 0
        def isJson = colEndFix == "\$"? 1 : 0

        if (isEnum > 0){
            typeStr = colFirstUpperName+colRestName+"e"
        }else if(isJson > 0){
            typeStr = colFirstUpperName+colJsonName+"Json"
        }

        def comm = [
                name : colName,
                type : typeStr,
                isEnum : isEnum,
                isJson : isJson,
                colName : col.getName(),
                comment: col.getComment(),
                annos: "@Column(name = \"" + col.getName() + "\")"]

        //if (modelName+"_id".equals(Case.LOWER.apply(col.getName()))) comm.annos += ["@Id"]

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

