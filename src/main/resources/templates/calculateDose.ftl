<#import "parts/common.ftl" as c>
<@c.page>
<script type="text/javascript">
    function showHide(element_arr) {
        //Если элемент с id-шником element_id существует
        for (var i=0; i<element_arr.length; i++) {
            if (document.getElementById(element_arr[i])) {
                //Записываем ссылку на элемент в переменную obj
                var obj2 = document.getElementById(element_arr[i]);
                //Если css-свойство display не block, то:
                if (obj2.style.display != "block") {
                    obj2.style.display = "block";
                }
                else {
                    obj2.style.display = "none";
                } //Скрываем элемент
            }
        }
    }

    <#--function changeRate(valueSel) {-->
        <#--if (document.getElementById("resChangeRate")) {-->
            <#--let res = ${result};-->
            <#--let resDose = ${sel_rate_dose};-->
            <#--res = res * resDose / Number(valueSel);-->
            <#--document.getElementById("resChangeRate").innerHTML = res;-->
        <#--}-->
    <#--}-->

</script>

<form method="post" action="/dose">
    <div class="row justify-content-center">
        <div class="card text-center  col-sm-8 mt-4">
            <div class="card-header">
                <ul class="nav nav-tabs card-header-tabs">
                    <li class="nav-item">
                        <a class="nav-link <#if (infution == "rate")>active</#if>" href="/dose/rate">Скорость вливания</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link <#if (infution == "calc_dose")>active</#if>" href="/dose/calc_dose">Дозировка</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link <#if (infution == "concentr")>active</#if>" href="/dose/concentr">Концентрация</a>
                    </li>
                </ul>
            </div>

            <div class="card-body">

                <div class="row justify-content-center">
                    <#if formError??>
                        <div class="alert alert-danger" role="alert">
                        ${formError}
                        </div>
                    </#if>
                </div>

                <#if (infution == "rate") || (infution == "concentr")>
                    <div class="form-group row mt-4">
                        <label class="col-form-label">Дозировка:</label>
                        <input
                                class="form-control small col-sm-3 mr-2 ml-2 ${(dose_fieldError??)?string('is-invalid', '')}"
                                name="dose_field"
                                value="<#if dose_field??>${dose_field}</#if>"
                                type="number" min="0.00000001" step="any">
                        <select class="custom-select col-sm-3 mr-2" name="sel_dose_weight" id="inputGroupSelect01">
                            <option <#if sel_dose_weight??><#if (sel_dose_weight=="1")>selected</#if></#if>
                                     value="1">микрограммы</option>
                            <option <#if sel_dose_weight??><#if (sel_dose_weight=="1000")>selected</#if></#if>
                                    value="1000">миллиграммы</option>
                            <option <#if sel_dose_weight??><#if (sel_dose_weight=="1000000")>selected</#if></#if>
                                    value="1000000">граммы</option>
                        </select>

                        <label id="id_label_weight" style="display: none;" class="col-form-label sm-2">/кг</label>
                        <label class="col-form-label">/</label>
                        <select class="custom-select col-sm-3 ml-2" name="sel_dose_time" id="inputGroupSelect01">
                            <option <#if sel_dose_time??><#if (sel_dose_time=="1")>selected</#if></#if>
                                    value="1">минуты</option>
                            <option <#if sel_dose_time??><#if (sel_dose_time=="60")>selected</#if></#if>
                                    value="60">часы</option>
                            <option <#if sel_dose_time??><#if (sel_dose_time=="1440")>selected</#if></#if>
                                    value="1440">дни</option>
                        </select>
                    </div>
                </#if>

                <#if (infution == "calc_dose") || (infution == "concentr")>
                    <div class="form-group row mt-3 mb-2">
                        <label class="col-form-label mr-2">Скорость вливания:</label>
                        <input class="form-control small col-sm-3 mr-2 ml-2 ${(rate_fieldError??)?string('is-invalid', '')}"
                               name="rate_field"
                               value="<#if rate_field??>${rate_field}</#if>"
                               type="number" min="0.00000001" step="any">
                        <select class="custom-select col-sm-3" name="sel_rate_dose" id="inputGroupSelect01">
                            <option <#if sel_rate_dose??><#if (sel_rate_dose=="60")>selected</#if></#if>
                                    value="60">мл/мин</option>
                            <option <#if sel_rate_dose??><#if (sel_rate_dose=="1")>selected</#if></#if>
                                    value="1">мл/час</option>
                            <option <#if sel_rate_dose??><#if (sel_rate_dose=="1440")>selected</#if></#if>
                                    value="1440">мл/день</option>
                        </select>
                    </div>
                </#if>

                <div class="form-group row mt-3">
                    <input type="checkbox" class="mr-3" style="zoom: 2;" onclick="showHide(['id_label_weight_for_calc', 'id_weight_field', 'id_label_weight'])">
                    <label class="col-form-label">Вес:</label>
                    <div id="id_weight_field" class="form-group" style="display: none;">
                        <input class="col-sm-5 mr-2" name="weight_field"
                               value="<#if weight_field??>${weight_field}</#if>"
                               type="number" min="1" step="any">
                        <label class="col-form-label">кг</label>
                    </div>
                </div>


                <#if (infution == "calc_dose") || (infution == "rate")>
                    <div class="form-group row mt-3 mb-0 offset-md-2">
                        <input class="form-control small col-sm-3 mr-2 ${(field_concentr_weightError??)?string('is-invalid', '')}"
                               name="field_concentr_weight"
                               value="<#if field_concentr_weight??>${field_concentr_weight}</#if>"
                               type="number" min="0.00000001" step="any">

                        <select class="custom-select col-sm-4" name="sel_concentr_weight" id="inputGroupSelect01">
                            <option <#if sel_concentr_weight??><#if (sel_concentr_weight=="0.001")>selected</#if></#if>
                                    value="0.001">микрограммы</option>
                            <option <#if sel_concentr_weight??><#if (sel_concentr_weight=="1")>selected</#if></#if>
                                    value="1">миллиграммы</option>
                            <option <#if sel_concentr_weight??><#if (sel_concentr_weight=="1000000")>selected</#if></#if>
                                    value="1000000">граммы</option>
                        </select>
                    </div>

                    <div class="form-group row mt-0 mb-0">
                        <label class="col-form-label mr-2">Концентрация:</label>
                        <hr size=3px width=400px align="left">
                    </div>

                    <div class="form-group row mt-0 mb-0 offset-md-2">
                        <input class="form-control small col-sm-3 mr-2 ${(dose_concentr_fieldError??)?string('is-invalid', '')}"
                               name="dose_concentr_field"
                               value="<#if dose_concentr_field??>${dose_concentr_field}</#if>"
                               type="number" min="0.00000001" step="any">
                        <select class="custom-select col-sm-3" name="sel_concentr_dose" id="inputGroupSelect01">
                            <option <#if sel_concentr_dose??><#if (sel_concentr_dose=="1")>selected</#if></#if>
                                    value="1">миллилитр</option>
                            <option <#if sel_concentr_dose??><#if (sel_concentr_dose=="1000")>selected</#if></#if>
                                    value="1000">литр</option>
                        </select>
                    </div>
                </#if>

                <div class="row justify-content-center mt-5">
                    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                    <button type="submit" class="btn btn-outline-primary mr-4">Посчитать</button>
                </div>

                <#if infution == "rate">
                    <div class="form-group row mt-3 mb-0">
                        <label class="col-form-label mr-2">Скорость вливания:</label>
                        <#if result??>
                            <label class="col-form-label mr-2 ml-2" id="resChangeRate">${result}</label>
                        </#if>
                        <select class="custom-select col-sm-3" name="sel_rate_dose" id="resSelRateDose" onchange="changeRate(this.value)">
                            <option <#if sel_rate_dose??><#if (sel_rate_dose=="60")>selected</#if></#if>
                                    value="60">мл/мин</option>
                            <option <#if sel_rate_dose??><#if (sel_rate_dose=="1")>selected</#if></#if>
                                    value="1">мл/час</option>
                            <option <#if sel_rate_dose??><#if (sel_rate_dose=="0.04166666666")>selected</#if></#if>
                                    value="0.04166666666">мл/день</option>
                        </select>
                    </div>
                </#if>

                <#if (infution == "calc_dose")>
                    <div class="form-group row mt-4">
                        <label class="col-form-label mr-2">Дозировка:</label>
                        <#if result??>
                            <label class="col-form-label mr-2 ml-2">${result}</label>
                        </#if>
                        <select class="custom-select col-sm-3 mr-2" name="sel_dose_weight" id="inputGroupSelect01">
                            <option <#if sel_dose_weight??><#if (sel_dose_weight=="1")>selected</#if></#if>
                                    value="1">микрограммы</option>
                            <option <#if sel_dose_weight??><#if (sel_dose_weight=="1000")>selected</#if></#if>
                                    value="1000">миллиграммы</option>
                            <option <#if sel_dose_weight??><#if (sel_dose_weight=="1000000")>selected</#if></#if>
                                    value="1000000">граммы</option>
                        </select>

                        <label id="id_label_weight_for_calc" style="display: none;" class="col-form-label sm-2">/кг</label>
                        <label class="col-form-label">/</label>
                        <select class="custom-select col-sm-3 ml-2" name="sel_dose_time" id="inputGroupSelect01">
                            <option <#if sel_dose_time??><#if (sel_dose_time=="1")>selected</#if></#if>
                                    value="1">минуты</option>
                            <option <#if sel_dose_time??><#if (sel_dose_time=="0.0166666666666667")>selected</#if></#if>
                                    value="0.0166666666666667">часы</option>
                            <option <#if sel_dose_time??><#if (sel_dose_time=="0.00069444444")>selected</#if></#if>
                                    value="0.00069444444">дни</option>
                        </select>
                    </div>
                </#if>

                <#if (infution == "concentr")>
                    <div class="form-group row mt-3 mb-0 offset-md-2">
                        <#if resultWeight??>
                            <label class="col-form-label mr-2">${resultWeight}</label>
                        </#if>
                        <select class="custom-select col-sm-4" name="sel_concentr_weight" id="inputGroupSelect01">
                            <option <#if sel_concentr_weight??><#if (sel_concentr_weight=="0.00001")>selected</#if></#if>
                                    value="0.00001">микрограммы</option>
                            <option <#if sel_concentr_weight??><#if (sel_concentr_weight=="0.01")>selected</#if></#if>
                                    value="0.01">миллиграммы</option>
                            <option <#if sel_concentr_weight??><#if (sel_concentr_weight=="10")>selected</#if></#if>
                                    value="10">граммы</option>
                        </select>
                    </div>

                    <div class="form-group row mt-0 mb-0">
                        <label class="col-form-label mr-2">Концентрация:</label>
                        <hr size=3px width=300px align="left">
                    </div>

                    <div class="form-group row mt-0 mb-0 offset-md-2">
                        <#if resultDose??>
                            <label class="col-form-label mr-2">${resultDose}</label>
                        </#if>
                        <select class="custom-select col-sm-3" name="sel_concentr_dose" id="inputGroupSelect01">
                            <option <#if sel_concentr_dose??><#if (sel_concentr_dose=="1")>selected</#if></#if>
                                    value="1">миллилитр</option>
                            <option <#if sel_concentr_dose??><#if (sel_concentr_dose=="1000")>selected</#if></#if>
                                    value="1000">литр</option>
                        </select>
                    </div>
                </#if>

            </div>
        </div>
    </div>
    <input type="hidden" name="infution" value="${infution}">
</form>
</@c.page>