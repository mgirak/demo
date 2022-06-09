<#import "parts/common.ftlh" as c>

<@c.page>
  <div class="p-2">Список дипломов</div>

  <form class="p-2" method="get" action="main">
    <input class="rounded" type="text" name="filter" value="${filter}">
    <button type="submit" class="btn btn-primary">Поиск по полям</button>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
  </form>

    <#list diplomas as diplomas>
      <div class="p-2 border-top border-dark">
        <b>${diplomas.title}</b><br>
        <span>Тип работы: ${diplomas.type}</span><br>
        <span>Студент: ${diplomas.student}, ${diplomas.direction}</span><br>
        <span>Научрук: ${diplomas.director}</span><br>
        <div>
          <a href="/pdfs/${diplomas.filename}">Открыть</a>
        </div>
      </div>
    </#list>

</@c.page>