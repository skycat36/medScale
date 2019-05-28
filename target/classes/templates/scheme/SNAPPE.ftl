<#import "*/parts/common.ftl" as c>

<@c.page>
<div class="form-group row justify-content-center">
    <h1><label class="col-ml-2 col-form-label">Схема SNAPPE</label></h1>
</div>
<form method="post" action="">
    <div class="container">
        <div class="row justify-content-center">
            <table class="table table-hover" style="width: 50%">
                <thead>
                <tr>
                    <th scope="col" style="width: 55%">Признак</th>
                    <th scope="col">Значение</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td scope="row">Среднее артериальное давление</td>
                    <td>
                        <select class="custom-select" name="param[]" id="inputGroupSelect01">
                            <option value="0">≥ 30 мм рт. ст.</option>
                            <option value="9">20-29 мм рт. ст.</option>
                            <option value="19">< 20 мм рт. ст.</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Наименьшая температура</td>
                    <td>
                        <select class="custom-select" name="param[]" id="inputGroupSelect01">
                            <option value="0">> 35,6⁰C</option>
                            <option value="8">35-35,6⁰C</option>
                            <option value="15">< 35,6⁰C</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td scope="row">PaO<small>2</small>/FiO<small>2</small>, %</td>
                    <td>
                        <select class="custom-select" name="param[]" id="inputGroupSelect01">
                            <option value="0">> 2.49</option>
                            <option value="5">1.0-2.49</option>
                            <option value="16">0,3-0,99</option>
                            <option value="28">< 0,3</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Наименьшее значение pH</td>
                    <td>
                        <select class="custom-select" name="param[]" id="inputGroupSelect01">
                            <option value="0">≥ 7.20</option>
                            <option value="7">7.10-7.19</option>
                            <option value="16">< 7.10</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Мультифокальные судороги</td>
                    <td><select class="custom-select" name="param[]" id="inputGroupSelect01">
                        <option value="0">Нет</option>
                        <option value="19">Да</option>
                    </select>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Почасовой диурез, мл/кг/ч</td>
                    <td>
                        <select class="custom-select" name="param[]" id="inputGroupSelect01">
                            <option value="0">≥ 1</option>
                            <option value="5">0.1-0.9</option>
                            <option value="18">< 0.1</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Оценка по Апгар</td>
                    <td>
                        <select class="custom-select" name="param[]" id="inputGroupSelect01">
                            <option value="0">≥ 7</option>
                            <option value="18">< 7</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Вес при рождении</td>
                    <td>
                        <select class="custom-select" name="param[]" id="inputGroupSelect01">
                            <option value="0">≥ 1000 г</option>
                            <option value="10">750-999</option>
                            <option value="17">< 750</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Задержка в/у развития</td>
                    <td>
                        <select class="custom-select" name="param[]" id="inputGroupSelect01">
                            <option value="0">> 3 перцентиля</option>
                            <option value="12">< 3 перцентиля</option>
                        </select>
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