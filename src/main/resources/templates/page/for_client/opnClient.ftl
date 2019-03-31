<#import "../../parts/common.ftl" as c>

<@c.page>
<div class="form-group row">
    <h1><label class="col-ml-2 col-form-label">База данных отделений</label></h1>
</div>

<div class="form-group row">
    <a type="button" class="btn btn-primary" href="/department/create_department">Создать отделение</a>
</div>

<table class="table">
    <thead>
    <tr>
        <#list colums as column>
            <th scope="col">${column}</th>
        </#list>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
        <#list opns as opn>
        <tr>
            <td>${opn.opn}</td>
            <td>
                <#if (user.id == 1)>
                    <div class="container mb-3">
                        <div class="row">
                            <form action="/department/delete/${opn.id}" method="post">
                                <div class="col-sm-2"><button type="submit">Удалить</div>
                                <input type="hidden" name="_csrf" value="${_csrf.token}" />
                            </form>
                            <div class="col-sm-1"><a href="/department/update/${opn.id}">Изменить</a></div>
                        </div>
                    </div>
                </#if>
            </td>
        </tr>
        </#list>
    </tbody>
</table>
</@c.page>