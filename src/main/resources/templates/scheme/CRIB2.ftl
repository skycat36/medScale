<#import "*/parts/common.ftl" as c>

<@c.page>
<div class="form-group row justify-content-center">
    <h1><label class="col-ml-2 col-form-label">Схема CRIB2</label></h1>
</div>
<div class="row justify-content-center">
    <#if gestationError??>
        <div class="alert alert-danger" role="alert">
        ${gestationError}
        </div>
    </#if>
</div>
<form method="post" action="">
    <div class="container">
        <div class="row justify-content-center">
            <table class="table table-hover" style="width: 55%">
                <thead>
                <tr>
                    <th scope="col" style="width: 65%">Признак</th>
                    <th scope="col">Значение</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td scope="row">Врожденные пороки (исключая несовместимые с жизнью)</td>
                    <td><select class="custom-select" name="param[]" id="inputGroupSelect01">
                        <option value="0">Нет</option>
                        <option value="1">Не остро опасные для жизни</option>
                        <option value="3">Остро опасные для жизни</option>
                    </select>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Максимальный избыток оснований BE в первые 12 ч, ммоль/л</td>
                    <td><input type="number" name="maxBE" step="any" value="<#if maxBE??>${maxBE}</#if>"
                               class="form-control small ${(maxBEError??)?string('is-invalid', '')}"
                               placeholder="Введите значение">
                        <#if maxBEError??>
                            <div class="invalid-feedback">
                            ${maxBEError}
                            </div>
                        </#if>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Минимальный FiO<small>2</small> в первые 12 ч</td>
                    <td>
                        <select class="custom-select" name="param[]" id="inputGroupSelect01">
                            <option value="0">Менее 0,4</option>
                            <option value="2">0,41-0,60</option>
                            <option value="3">0,61-0,90</option>
                            <option value="4">0,91-1,00</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Максимальный FiO<small>2</small> в первые 12 ч</td>
                    <td>
                        <select class="custom-select" name="param[]" id="inputGroupSelect01">
                            <option value="0">Менее 0,4</option>
                            <option value="1">0,41-0,80</option>
                            <option value="3">0,81-0,90</option>
                            <option value="5">0,91-1,00</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Температура при поступлении</td>
                    <td><input type="number" name="temp" step="any" value="<#if temp??>${temp}</#if>"
                               class="form-control small ${(tempError??)?string('is-invalid', '')}"
                               placeholder="Введите значение">
                        <#if tempError??>
                            <div class="invalid-feedback">
                            ${tempError}
                            </div>
                        </#if>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="row justify-content-center"><button type="submit" class="btn btn-primary btn-lg mb-5" onclick="calc()">Добавить</button></div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
</form>
</@c.page>