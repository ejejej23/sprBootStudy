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
    def className = javaName(table.getName(), true)
    def fields = calcFields(table)
    packageName = getPackageName(dir)
    packageParentName = getPackageParentName()
    new File(dir, entityName + ".java").withPrintWriter { out -> generate(out, className, fields) }
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
    out.println ""
    out.println "import com.naon.framework.annotation.Comment;"
    out.println "import ${packageRootName}.core.utils.IdGenerator;"
    out.println "import ${packageParentName}.dto.${entityName}Dto;"

    out.println "import lombok.AccessLevel;"
    out.println "import lombok.Builder;"
    out.println "import lombok.Getter;"
    out.println "import lombok.NoArgsConstructor;"
    out.println "import org.springframework.data.annotation.CreatedDate;"
    out.println "import org.springframework.data.annotation.LastModifiedDate;"
    out.println "import javax.persistence.*;"
    out.println "import org.springframework.data.domain.Persistable;"
    out.println "import org.springframework.data.jpa.domain.support.AuditingEntityListener;"
    out.println "import java.util.Optional;"

    Set types = new HashSet()

    fields.each() {
        types.add(it.type)

        if(it.isEnum > 0){
            out.println "import ${packageParentName}.enums.${it.type};"
        }
        if(it.isJson > 0){
            out.println "import ${packageParentName}.json.${it.type};"
            out.println "import org.hibernate.annotations.Type;"
            //out.println "import ${packageParentName}.json.converter.${it.type2}Converter;"
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
    out.println "@Entity"
    out.println "@Table(name = \"$tableName\")"
    out.println "@Getter"
    out.println "@EntityListeners(AuditingEntityListener.class)"
    out.println "@NoArgsConstructor(access = AccessLevel.PROTECTED)"

    def idType = "";

    fields.eachWithIndex{it,index->
        if(index < 1) {
            idType = it.type
        }
    }
    out.println "public class $entityName implements Persistable<${idType}> {"
    out.println ""
    def idName = ""

    fields.eachWithIndex{it,index->
        if(index < 1) {
            out.println "  @Id"
            out.println "  @GeneratedValue(strategy = GenerationType.IDENTITY)"
            idName = it.name
        }
    }

    fields.each() {
        if (it.isJson > 0) out.println "  @Type(type = \"jsonb\")"
        if (it.name == "regDt") out.println "  @CreatedDate"
        if (it.name == "updDt") out.println "  @LastModifiedDate"
        if (it.annos != "") out.println "  ${it.annos}"
        if (it.comment != null) out.println "  @Comment(value = \"${it.comment}\")"
        if (it.isEnum > 0) out.println "  @Enumerated(EnumType.STRING)"
        //if (it.isJson > 0) out.println "  @Convert(converter = ${it.type2}Converter.class)"

        out.println "  private ${it.type} ${it.name};"
        out.println ""
    }
    out.println ""
    def assignUpdate = "";
    def assignBuilder = "";

    def paramBuilder = []
    fields.eachWithIndex{it,index->
        if(index > 0 && it.name != "regDt" && it.name != "updDt") {
            assignBuilder += "    this.${it.name} = ${it.name};\r\n"
            paramBuilder.add("${it.type} ${it.name}");
            assignUpdate += "    Optional.ofNullable(dto.get${javaName(it.name, true)}()).ifPresent(${it.name} -> this.${it.name} = ${it.name});\r\n"
        }
    }

    out.println "  @Builder"
    out.println "  public ${entityName}(${paramBuilder.join(",")}) {"
    out.println assignBuilder;
    out.println "  }"

    out.println "  public ${entityName} update${entityName}(${entityName}Dto.Update dto) {"
    out.println assignUpdate
    out.println "    return this;"
    out.println "  }"

//    out.println ""
//    out.println "  @PrePersist"
//    out.println "  public void prePersist() {"
//    out.println "      this.${idName} = Optional.ofNullable(this.${idName}).orElseGet(IdGenerator::generate16UniqueId);"
//    out.println "  }"

    out.println ""
    out.println "  @Override"
    out.println "  public ${idType} getId() {"
    out.println "      return ${idName};"
    out.println "  }"

    out.println ""
    out.println "  @Override"
    out.println "  public boolean isNew() {"
    out.println "      return true;"
    out.println "  }"
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
        def typeStr2 = typeStr

        if (isEnum > 0){
            typeStr = colFirstUpperName+colRestName+"e"
        }else if(isJson > 0){
            typeStr = colFirstUpperName+colJsonName+"Json"
            typeStr2 = colFirstUpperName+colJsonName
        }


        def comm = [
                name : colName,
                type : typeStr,
                type2 : typeStr2,
                isEnum : isEnum,
                isJson : isJson,
                colName : col.getName(),
                comment: col.getComment(),
                annos: "@Column(name = \"" + col.getName() + "\"" +(isJson > 0 ? ", columnDefinition = \"jsonb\"" : "")+")"]

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