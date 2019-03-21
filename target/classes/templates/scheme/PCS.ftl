<#import "*/parts/common.ftl" as c>

<@c.page>
<div class="form-group row justify-content-center">
    <h1><label class="col-ml-2 col-form-label">Схема SOFA</label></h1>
</div>
<form name="formPCS" method="post" action="">
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
                    <td scope="row">Открывание глаз:</td>
                    <td>
                        <select class="custom-select" name="param[]" id="inputGroupSelect01">
                            <option value="4">спонтанное</option>
                            <option value="3">в ответ на обращение</option>
                            <option value="2">в ответ на боль</option>
                            <option value="1">нет реакции</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Наилучшая вербальная реакция:</td>
                    <td>
                        <select class="custom-select" name="param[]" id="inputGroupSelect01">
                            <option value="5">ориентирован</option>
                            <option value="4">произносит отдельные слова</option>
                            <option value="3">произносит отдельные звуки</option>
                            <option value="2">крик, плач</option>
                            <option value="1">нет реакции</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Наилучший двигательный ответ:</td>
                    <td>
                        <select class="custom-select" name="param[]" id="inputGroupSelect01">
                            <option value="5">выполняет команды</option>
                            <option value="4">локализует источник боли</option>
                            <option value="3">сгибание конечностей в ответ на боль</option>
                            <option value="2">разгибание конечностей в ответ на боль</option>
                            <option value="1">нет реакции</option>
                        </select>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="row justify-content-center">
            <button type="submit" id="result" name="result" class="btn btn-primary btn-lg mb-5">Добавить</button>
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
</form>
</@c.page>