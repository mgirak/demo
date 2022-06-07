<#macro diplomasShow>

    <#list diplomas as diplomas>
      <div class="p-2 border-top border-dark">
        <b>${diplomas.title}</b><br>
        <span>Студент: ${diplomas.student} группы ${diplomas.direction}</span><br>
        <span>Научный руководитель: ${diplomas.director}</span><br>
        <div>
          <a href="/pdfs/${diplomas.filename}"> открыть диплом</a>
        </div>
          <#nested >
      </div>
    </#list>
</#macro>