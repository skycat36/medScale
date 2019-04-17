<#macro actionWith path isClient>
<form method="get" action="<#if isClient>/client_list<#else>/worker_list</#if>">
    <#if textError??>
        <div class="alert alert-danger" role="alert">
        ${textError}
        </div>
    </#if>
    <div class="container mb-3">
        <div class="row">
            <div class="col-sm-3 mr-0"><input type="text" name="filter" class="form-control ${(textError??)?string('is-invalid', '')}" value="${filter!""}" placeholder="Поиск"></div>
            <div class="col-sm-1"><button type="submit" class="btn btn-primary ml-0">Найти</button></div>
            <#if admin??>
                <div class="col-sm-4 ml-5"><a type="button"  class="btn btn-primary" href="/edit_profile_worker/create_profile_worker">Создать сотрудника</a></div>
            </#if>
        </div>
    </div>
    <#if isClient>
        <div class="input-group col-md-3">
            <div class="input-group mb-3">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Поиск</label>
                </div>
                <select class="custom-select" name="selectedField" id="inputGroupSelect01">
                    <option  value="fam">по фамилии</option>
                    <option value="opn">по отделению</option>
                    <option value="birthdate">по дате рождения</option>
                </select>
            </div>
        </div>
    </#if>
</form>
<table class="table">
    <thead>
    <tr>
        <#list colums as column>
            <th scope="col">${column}</th>
        </#list>
        <#if isClient><th scope="col"></th></#if>
    </tr>
    </thead>
    <tbody>
        <#if isClient>
            <#list users as user>
            <tr>
                <td>${user.fam}</td>
                <td>${user.name}</td>
                <td>${user.secName}</td>
                <td>${usersOpn[user_index]}</td>
                <td>${user.birthdate}</td>
                <td>${user.dateOfArrival}</td>
                <td><#if user.dateOfDeparture??>${user.dateOfDeparture}<#else>Нет</#if></td>
                <td><#if user.dateOfDeath??>${user.dateOfDeath}<#else>Нет</#if></td>
                <td>
                    <div class="container mb-3">
                        <div class="row">
                            <form action="/client_list/delete/${user.id}" method="post">
                                <div class="col-sm-2"><button type="submit">Удалить</div>
                                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            </form>
                            <div class="col-sm-1"><a href="/client_list/select/${user.id}">Изменить</a></div>
                        </div>
                    </div>
                </td>
            </tr>
            </#list>
        <#else>
            <#list users as user>
            <tr>
                <td>${user.fam}</td>
                <td>${user.name}</td>
                <td>${user.secName}</td>
                <td>${user.position}</td>
                <#if admin??>
                    <td>
                        <#if (user.id != 1)>
                            <div class="container mb-3">
                                <div class="row">
                                    <form action="/edit_profile_worker/delete_some_profile/${user.id}" method="post">
                                        <div class="col-sm-2"><button type="submit">Удалить</div>
                                        <input type="hidden" name="_csrf" value="${_csrf.token}" />
                                    </form>
                                    <div class="col-sm-1"><a href="/edit_profile_worker/change_user/${user.id}">Изменить</a></div>
                                </div>
                            </div>
                        </#if>
                    </td>
                </#if>
            </tr>
            </#list>
        </#if>
    </tbody>
</table>
</#macro>