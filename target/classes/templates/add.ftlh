<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>


<@c.page>
    <@l.logout/>

  <div class="p-2 card-columns">
    <form method="post" enctype="multipart/form-data">
      <input type="file" name="file">
      <input type="hidden" name="_csrf" value="${_csrf.token}"/>
      <button type="submit" style="background: #2f62e8; color: white;">Добавить</button>
      <#if exeption??>
        ${exeption}
      </#if>
    </form>

  </div>


  <div class="p-2">Список дипломов</div>

    <#list diplomas as diplomas>
      <div class="p-2 border-top border-dark">
        <b>${diplomas.title}</b><br>
        <span>Тип работы: ${diplomas.type}</span><br>
        <span>Студент: ${diplomas.student} группы ${diplomas.direction}</span><br>
        <span>Научный руководитель: ${diplomas.director}</span><br>
        <div>
          <a href="/pdfs/${diplomas.filename}">Открыть</a>
          <a href="/delete/${diplomas.id}"> Удалить</a>
        </div>
      </div>
    </#list>

</@c.page>