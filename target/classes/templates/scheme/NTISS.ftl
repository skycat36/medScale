<#import "*/parts/common.ftl" as c>

<@c.page>
<script>
    function onlyOne(checkbox, nameEl) {
        var checkboxes = document.getElementsByName(nameEl)
        checkboxes.forEach((item) => {
            if (item !== checkbox) item.checked = false
        })
    }

    function calc(nameForm) {
        var allElem = document.forms[nameForm];
        var p=0;

        for (var i = 0; i<allElem.length; i++) {
            if (allElem.elements[i].checked){
                p += parseInt(allElem.elements[i].value);
            }
        }
        document.getElementById('result').value = p;
    }

</script>
<div class="form-group row justify-content-center">
    <h1><label class="col-ml-2 col-form-label">Схема NTISS</label></h1>
</div>
<form name="formNtiss" method="post" action="">
    <div class="container">
        <div class="row justify-content-center">
            <table class="table" style="width: 70%">
                <thead>
                <tr>
                    <th scope="col" style="width: 30%">Признак</th>
                    <th scope="col">Значение</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td scope="row">Респираторный</td>
                    <td>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[0]"
                                       type="checkbox" onclick="onlyOne(this, 'param[0]')" value="1">
                                Дополнительный кислород [1]
                            </label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[0]"
                                       type="checkbox" onclick="onlyOne(this, 'param[0]')" value="3">
                                Механическая вентиляция легких [1]</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[0]"
                                       type="checkbox" onclick="onlyOne(this, 'param[0]')" value="4">
                                Механическая вентиляция легких с мышечой релаксацией [1]</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[0]"
                                       type="checkbox" onclick="onlyOne(this, 'param[0]')" value="4">
                                Высокочастотная вентиляция легких [1]</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[0]"
                                       type="checkbox" onclick="onlyOne(this, 'param[0]')" value="2">
                                Применение постоянного положительного давления в дыхательных путях[1]</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[1]"
                                       type="checkbox" onclick="onlyOne(this, 'param[1]')" value="1">
                                Уход за трахеостмой [2]</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[1]"
                                       type="checkbox" onclick="onlyOne(this, 'param[1]')" value="1">
                                Наложение трахеостмы [2]</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[2]" type="checkbox" value="1">
                                Применение сурфактанта</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[3]" type="checkbox" value="2">
                                Эндотрахеальная интубация</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[4]" type="checkbox" value="4">
                                Экстракорпоральная мембранная оксигенация</label>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Кардиоваскулярный</td>
                    <td>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[5]"
                                       type="checkbox" onclick="onlyOne(this, 'param[5]')" value="1">
                                Нагрузка объемом (болюсное введение р-ров) (<=15 мл/кг) [3]</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[5]"
                                       type="checkbox" onclick="onlyOne(this, 'param[5]')" value="3">
                                Нагрузка объемом (>15 мл/кг) [3]</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[6]"
                                       type="checkbox" onclick="onlyOne(this, 'param[6]')" value="2">
                                Использование вазопрессоров (1 препарат) [4]</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[6]"
                                       type="checkbox" onclick="onlyOne(this, 'param[6]')" value="3">
                                Использование вазопрессоров (>1 препарата) [4]</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[7]"
                                       type="checkbox" onclick="onlyOne(this, 'param[7]')" value="3">
                                Резервный пейсмекер [5]</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[7]"
                                       type="checkbox" onclick="onlyOne(this, 'param[7]')" value="4">
                                Функционирующий пейсмекер [5]</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[8]" type="checkbox" value="1">
                                Введение индометацина</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[9]" type="checkbox" value="4">
                                Кардиопульмональная реанимация</label>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Лекарственная терапия</td>
                    <td>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[10]"
                                       type="checkbox" onclick="onlyOne(this, 'param[10]')" value="1">
                                Использование антибиотиков (<=2 препаратов)[6]</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[10]"
                                       type="checkbox" onclick="onlyOne(this, 'param[10]')" value="2">
                                Использование антибиотиков (>2 препаратов) [6]</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[11]"
                                       type="checkbox" onclick="onlyOne(this, 'param[11]')" value="1">
                                Использование диуретиков (энтерально)[7]</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[11]"
                                       type="checkbox" onclick="onlyOne(this, 'param[11]')" value="2">
                                Использование диуретиков (парентерально) [7]</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[12]" type="checkbox" value="1">
                                Использование стероидов (постнатально)</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[13]" type="checkbox" value="1">
                                Использование антиконвульсантов</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[14]" type="checkbox" value="1">
                                Использование аминофилина</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[15]" type="checkbox" value="1">
                                Другие внеплановые медикаменты</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[16]" type="checkbox" value="3">
                                Лечение метаболического ацидоза</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[17]" type="checkbox" value="3">
                                Калийсвязывающие растворы и припараты</label>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Мониторинг</td>
                    <td>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[18]"
                                       type="checkbox" onclick="onlyOne(this, 'param[18]')" value="1">
                                Венесекция (забор от 5 до 10 проб крови) [8]</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[18]"
                                       type="checkbox" onclick="onlyOne(this, 'param[18]')" value="2">
                                Активная венесекция (>10 заборов проб крови) [8]</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[19]" type="checkbox" value="1">
                                Частое определение состояния витальных функций</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[20]" type="checkbox" value="1">
                                Кардиораспираторный мониторинг</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[21]" type="checkbox" value="1">
                                Строгая терморегуляция окружающей среды</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[22]" type="checkbox" value="1">
                                Неинвазивный мониторинг оксигенотерапии</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[23]" type="checkbox" value="1">
                                Мониторинг артериального давления</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[24]" type="checkbox" value="1">
                                Мониторинг центрального венозного давления</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[25]" type="checkbox" value="1">
                                Мочевой катктер</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[26]" type="checkbox" value="1">
                                Строгий количественный учет введенного и выведенного</label>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Метаболизм/питание</td>
                    <td>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[27]" type="checkbox" value="1">
                                Зондовое питание</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[28]" type="checkbox" value="1">
                                Внутревенные жировые эмульсии</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[29]" type="checkbox" value="1">
                                Внутривенные аминокислотные растворы</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[30]" type="checkbox" value="1">
                                Фототерапия</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[31]" type="checkbox" value="2">
                                Использование инсулина</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[32]" type="checkbox" value="3">
                                Инфузия концентрированных растворов калия</label>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Трансфузия</td>
                    <td>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[33]"
                                       type="checkbox" onclick="onlyOne(this, 'param[33]')" value="2">
                                Трансфузия эритроцитарной массы (<=15 мл/кг) [9]</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[33]"
                                       type="checkbox" onclick="onlyOne(this, 'param[33]')" value="3">
                                Трансфузия эритроцитарной массы (>15 мл/кг) [9]</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[34]" type="checkbox" value="1">
                                Внутревенный имуноглобулин</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[35]" type="checkbox" value="2">
                                Частичная обменная трансфузия</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[36]" type="checkbox" value="3">
                                Трансфузия тромбовзвеси</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[37]" type="checkbox" value="3">
                                Трансфузия лейковзвеси</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[38]" type="checkbox" value="3">
                                Повторная обменная трансфузия</label>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Процедуры</td>
                    <td>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[39]"
                                       type="checkbox" onclick="onlyOne(this, 'param[39]')" value="2">
                                Один дренаж в грудной клетке [11]</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[39]"
                                       type="checkbox" onclick="onlyOne(this, 'param[39]')" value="3">
                                Несколько дренажей в грудной клетке [11]</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[40]"
                                       type="checkbox" onclick="onlyOne(this, 'param[40]')" value="2">
                                Малое оперативное вмешательство [12]</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[40]"
                                       type="checkbox" onclick="onlyOne(this, 'param[40]')" value="4">
                                Большое (полостное) оперативное вмешательство [12]</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[41]"
                                       type="checkbox" onclick="onlyOne(this, 'param[41]')" value="4">
                                Перикардиоцентез [13]</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[41]"
                                       type="checkbox" onclick="onlyOne(this, 'param[41]')" value="4">
                                Перикардиальный дренаж [13]</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[42]" type="checkbox" value="2">
                                Транспортировка пациента</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[43]" type="checkbox" value="3">
                                Торакоцентез</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[44]" type="checkbox" value="4">
                                Диализ</label>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td scope="row">Сосудистый доступ</td>
                    <td>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[45]" type="checkbox" value="1">
                                Переферический венозный катетер</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[46]" type="checkbox" value="2">
                                Артериальная линия</label>
                        </div>
                        <div class="form-check">
                            <label class="form-check-label">
                                <input class="form-check-input" name="param[47]" type="checkbox" value="2">
                                Центральный венозный катетр(линия)</label>
                        </div>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="row justify-content-center">
            <button type="submit" id="result" name="result" class="btn btn-primary btn-lg mb-5" onclick="calc('formNtiss')">Добавить</button>
        </div>
    </div>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
</form>
</@c.page>