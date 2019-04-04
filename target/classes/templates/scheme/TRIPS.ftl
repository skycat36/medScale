<#import "*/parts/common.ftl" as c>

<@c.page>
<div class="form-group row justify-content-center">
    <h1><label class="col-ml-2 col-form-label">Схема TRIPS</label></h1>
</div>
<form name="formTRIPS" method="post" action="">
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
                    <td scope="row">Температура</td>
                    <td>
                        <select class="custom-select" name="param[]" id="inputGroupSelect01">
                            <option value="8">< 36.1</option>
                            <option value="1">36.1 - 36.5</option>
                            <option value="0">36.6 - 37.1</option>
                            <option value="1">37.2 - 37.6</option>
                            <option value="8">> 37.6</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Респираторный статус</td>
                    <td>
                        <select class="custom-select" name="param[]" id="inputGroupSelect01">
                            <option value="14">Тяжелая дисфункция(апноэ, дыхание типа гаспинг, интубированные)</option>
                            <option value="5">Умеренная дисфункция(ЧДД > 60 или SpO2 < 85%)</option>
                            <option value="0">Низкая или умеренная (ЧДД ≤ 60 или SpO2 ≥ 85%)</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Систологическое артериальное давление</td>
                    <td>
                        <select class="custom-select" name="param[]" id="inputGroupSelect01">
                            <option value="26">< 20 мм рт. ст.</option>
                            <option value="16">20 - 40 мм рт. ст.</option>
                            <option value="0">> 40 мм рт. ст.</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Ответ на болевые стимулы</td>
                    <td>
                        <select class="custom-select" name="param[]" id="inputGroupSelect01">
                            <option value="17">Нет, мышечная релаксация</option>
                            <option value="6">Летаргия, нет плача</option>
                            <option value="0">Крик, раздражительность, повышенное потоотделение</option>
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