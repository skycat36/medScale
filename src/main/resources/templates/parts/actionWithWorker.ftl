<#macro action path nameAction isSave>
<div class="form-group row">
    <h1><label class="col-ml-2 col-form-label">${nameAction}</label></h1>
</div>
<form action="${path}" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Логин :</label>
        <div class="col-sm-3">
            <input type="text" name="login" value="<#if user??>${user.login}</#if>"
                   class="form-control small ${(loginError??)?string('is-invalid', '')}"
                   placeholder="Логин"/>
            <#if loginError??>
                <div class="invalid-feedback">
                ${loginError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Пароль :</label>
        <div class="col-sm-3">
            <input type="password" name="password"
                   class="form-control small ${(passwordError??)?string('is-invalid', '')}"
                   placeholder="Введите новый пароль"/>
            <#if passwordError??>
                <div class="invalid-feedback">
                ${passwordError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Повторите пароль :</label>
        <div class="col-sm-3">
            <input type="password" name="password2"
                   class="form-control small ${(password2Error??)?string('is-invalid', '')}"
                   placeholder="Повторите пароль"/>
            <#if password2Error??>
                <div class="invalid-feedback">
                ${password2Error}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Фамилия :</label>
        <div class="col-sm-3">
            <input type="text" name="fam" value="<#if user??>${user.fam}</#if>"
                   class="form-control small ${(famError??)?string('is-invalid', '')}"
                   placeholder="Фамилия"/>
            <#if famError??>
                <div class="invalid-feedback">
                ${famError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Имя :</label>
        <div class="col-sm-3">
            <input type="text" name="name" value="<#if user??>${user.name}</#if>"
                   class="form-control small ${(nameError??)?string('is-invalid', '')}"
                   placeholder="Имя"/>
            <#if nameError??>
                <div class="invalid-feedback">
                ${nameError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Отчество :</label>
        <div class="col-sm-3">
            <input type="text" name="secName" value="<#if user??>${user.secName}</#if>"
                   class="form-control small ${(secNameError??)?string('is-invalid', '')}"
                   placeholder="Отчество"/>
            <#if secNameError??>
                <div class="invalid-feedback">
                ${secNameError}
                </div>
            </#if>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Должность :</label>
        <div class="col-sm-3">
            <textarea name="position" class="form-control small ${(positionError??)?string('is-invalid', '')}"
                      placeholder="Должность"><#if user??>${user.position}</#if></textarea>
            <#if positionError??>
                <div class="invalid-feedback">
                ${positionError}
                </div>
            </#if>
        </div>
    </div>

    <div class="form-group row">
        <#if isSave>
            <div class="col-sm-1 mr-4"><button type="submit" class="btn btn-primary ml-0">Сохранить</button></div>
        <#else>
            <div class="col-sm-1"><button type="submit" class="btn btn-primary ml-0">Создать профиль</button></div>
        </#if>
        <input type="hidden" value="${_csrf.token}" name="_csrf">

</form>

    <#if isSave>
        <#if user?exists>
            <#if user.id != 1>
            <form method="post" action="/edit_profile_worker/delete/${user.id}">
                <div class="col-sm-1"><button type="submit" class="btn btn-primary ml-0">Удалить профиль</button></div>
                <input type="hidden" value="${_csrf.token}" name="_csrf">
            </form>
            </#if>
        </#if>
    </#if>
</div>
</#macro>