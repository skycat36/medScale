<#macro action path nameAction isSave>
<div class="form-group row">
    <h1><label class="col-ml-2 col-form-label">${nameAction}</label></h1>
</div>
<form action="${path}" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Название отделения :</label>
        <div class="col-sm-3">
            <input type="text" name="nameOpn" value="<#if nameOpn??>${nameOpn}</#if>"
                   class="form-control small ${(nameOpnError??)?string('is-invalid', '')}"
                   placeholder="Исход"/>
            <#if nameOpnError??>
                <div class="invalid-feedback">
                ${nameOpnError}
                </div>
            </#if>
        </div>
    </div>


    <div class="form-group row">
        <div class="col-sm-1"><button type="submit" class="btn btn-primary ml-0"><#if isSave>Сохранить<#else>Создать отделение</#if></button></div>
        <input type="hidden" value="${_csrf.token}" name="_csrf">
    </div>
</form>
</#macro>