<#import "*/parts/common.ftl" as c>

<@c.page>
<div class="form-group row justify-content-center">
    <h1><label class="col-ml-2 col-form-label">Схема SOFA</label></h1>
</div>
<form name="formSOFA" method="post" action="">
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
                    <td scope="row">PaO<small>2</small>/FiO<small>2</small></td>
                    <td>
                        <select class="custom-select" name="param[]" id="inputGroupSelect01">
                            <option value="0">≥400</option>
                            <option value="1">300-399</option>
                            <option value="2">200-299</option>
                            <option value="3">100-199</option>
                            <option value="4"><100</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Уровень тромбоцитов, мкл</td>
                    <td>
                        <select class="custom-select" name="param[]" id="inputGroupSelect01">
                            <option value="0">≥150.000</option>
                            <option value="1">100.000-149.999</option>
                            <option value="2">50.000-99.999</option>
                            <option value="3">20.000-49.99</option>
                            <option value="4"><20.000</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Билирубин, мг/дл</td>
                    <td>
                        <select class="custom-select" name="param[]" id="inputGroupSelect01">
                            <option value="0"><1.2</option>
                            <option value="1">1.2-1.9</option>
                            <option value="2">2.0-5.9</option>
                            <option value="3">6.0-11.9</option>
                            <option value="4">≥12.0</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Гипотензия</td>
                    <td>
                        <select class="custom-select" name="param[]" id="inputGroupSelect01">
                            <option value="0">Среднее АД ≥ 70 мм рт.ст</option>
                            <option value="1">Среднее АД < 70 мм рт.ст без использования вазосоров</option>
                            <option value="2">Добутамин, любая дозировка</option>
                            <option value="2">Допамин ≤ 5 мкг/кг/мин.</option>
                            <option value="3">Допамин > 5-15 мкг/кг/мин.</option>
                            <option value="4">Допамин > 15 мкг/кг/мин.</option>
                            <option value="3">Эпинефрин ≤ 0.1 мкг/кг/мин.</option>
                            <option value="4">Эпинефрин > 0.1 мкг/кг/мин.</option>
                            <option value="3">Норэпинефрин ≤ 0.1 мкг/кг/мин.</option>
                            <option value="4">Норэпинефрин > 0.1 мкг/кг/мин.</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Шкала комы Глазго, баллы</td>
                    <td>
                        <select class="custom-select" name="param[]" id="inputGroupSelect01">
                            <option value="0">15</option>
                            <option value="1">13-14</option>
                            <option value="2">10-12</option>
                            <option value="3">6-9</option>
                            <option value="4">3-5</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Креатин и диурез</td>
                    <td><select class="custom-select" name="param[]" id="inputGroupSelect01">
                        <option value="0">Креатин < 1.2 мг/мл</option>
                        <option value="1">Креатин 1.2-1.9 мг/мл</option>
                        <option value="2">Креатин 2.0-3.4 мг/мл</option>
                        <option value="3">Креатин 3.5-4.9 мг/мл</option>
                        <option value="4">Креатин > 5.0 мг/мл</option>
                        <option value="3">Диурез 200-499 мл в день</option>
                        <option value="4">Диурез < 200 мл в день</option>
                    </select>
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