<#macro login path>

  <form class="p-2" action="${path}" method="post">
    <div><label> Имя пользователя: <input type="text" name="username"/> </label></div>
    <div><label> Пароль: <input type="password" name="password"/> </label></div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <div><input type="submit" value="Sign In"/></div>
  </form>

</#macro>

<#macro logout>

  <form class="p-2" action="/logout" method="post">
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input style="background: #2f62e8; color: white;" type="submit" value="Sign Out"/>
  </form>

</#macro>