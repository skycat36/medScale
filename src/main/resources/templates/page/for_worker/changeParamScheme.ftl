<#import "../../parts/common.ftl" as c>

<@c.page>
<div class="form-group row justify-content-center">
    <h1><label class="col-ml-2 col-form-label">Обновить параметры схем</label></h1>
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
                    <th scope="col" style="width: 25%">Название схемы</th>
                    <th scope="col">Балл</th>
                    <th scope="col">Процент</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td scope="row">NTISS</td>
                    <td><input type="number" name="ballNtiss" step="0" value="<#if ntiss.colibrBall??>${ntiss.colibrBall}</#if>"
                               class="form-control small ${(ballNtissError??)?string('is-invalid', '')}"
                               placeholder="Введите значение">
                    </td>
                    <td><input type="number" name="procNtiss" step="0" value="<#if ntiss.colibrProc??>${ntiss.colibrProc}</#if>"
                               class="form-control small ${(procNtissError??)?string('is-invalid', '')}"
                               placeholder="Введите значение">
                    </td>
                </tr>
                <tr>
                    <td scope="row">PCS</td>
                    <td>

                    </td>
                    <td><input type="number" name="procPcs" step="0" value="<#if pcs.colibrProc??>${pcs.colibrProc}</#if>"
                               class="form-control small ${(procPcsError??)?string('is-invalid', '')}"
                               placeholder="Введите значение">
                    </td>
                </tr>
                <tr>
                    <td scope="row">SNAPPE</td>
                    <td><input type="number" name="ballSnappe" step="0" value="<#if snappe.colibrBall??>${snappe.colibrBall}</#if>"
                               class="form-control small ${(ballSnappeError??)?string('is-invalid', '')}"
                               placeholder="Введите значение">
                    </td>
                    <td><input type="number" name="procSnappe" step="0" value="<#if snappe.colibrProc??>${snappe.colibrProc}</#if>"
                               class="form-control small ${(procSnappeError??)?string('is-invalid', '')}"
                               placeholder="Введите значение">
                    </td>
                </tr>
                <tr>
                    <td scope="row">SOFA</td>
                    <td><input type="number" name="ballSofa" step="0" value="<#if sofa.colibrBall??>${sofa.colibrBall}</#if>"
                               class="form-control small ${(ballSofaError??)?string('is-invalid', '')}"
                               placeholder="Введите значение">
                    </td>
                    <td><input type="number" name="procSofa" step="0" value="<#if sofa.colibrProc??>${sofa.colibrProc}</#if>"
                               class="form-control small ${(procSofaError??)?string('is-invalid', '')}"
                               placeholder="Введите значение">
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="row justify-content-center"><button type="submit" class="btn btn-primary btn-lg mb-5">Добавить</button></div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
</form>
</@c.page>