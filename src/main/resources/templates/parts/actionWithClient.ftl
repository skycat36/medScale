<#macro action path nameAction isSave>
<div class="form-group row">
    <h1><label class="col-ml-2 col-form-label">${nameAction}</label></h1>
</div>
<form action="${path}" method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Фамилия :</label>
        <div class="col-sm-3">
            <input type="text" name="fam"
                   value="<#if client??><#if client.fam??>${client.fam}</#if></#if>"
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
            <input type="text" name="name"
                   value="<#if client??><#if client.name??>${client.name}</#if></#if>"
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
            <input type="text" name="secName"
                   value="<#if client??><#if client.secName??>${client.secName}</#if></#if>"
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
        <label class="col-sm-2 col-form-label">Дата рождения :</label>
        <div class="col-sm-3">
            <input type="date" name="birthdate"
                   value="<#if client??><#if client.birthdate??>${client.birthdate}</#if></#if>"
                   class="form-control small ${(birthdateError??)?string('is-invalid', '')}"
                   placeholder="Дата рождения"/>
            <#if birthdateError??>
                <div class="invalid-feedback">
                ${birthdateError}
                </div>
            </#if>
        </div>
        <label class="col-sm-2 col-form-label">Дата прибытия :</label>
        <div class="col-sm-3">
            <input type="date" name="dateOfArrival"
                   value="<#if client??><#if client.dateOfArrival??>${client.dateOfArrival}</#if></#if>"
                   class="form-control small ${(dateOfArrivalError??)?string('is-invalid', '')}"
                   placeholder="Дата рождения"/>
            <#if dateOfArrivalError??>
                <div class="invalid-feedback">
                ${dateOfArrivalError}
                </div>
            </#if>
        </div>
    </div>
    <#if isSave>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Дата обследования :</label>
        <div class="col-sm-3">
            <input type="date" name="survayDate" value="<#if client??><#if client.survayDate??>${client.survayDate}</#if></#if>"
                   class="form-control small ${(survayDateError??)?string('is-invalid', '')}"
                   placeholder="Дата рождения"/>
            <#if survayDateError??>
                <div class="invalid-feedback">
                ${survayDateError}
                </div>
            </#if>
        </div>

        <label class="col-sm-2 col-form-label">Дата выписки :</label>
        <div class="col-sm-3">
            <input type="date" name="dateOfDeparture" value="<#if client??><#if client.dateOfDeparture??>${client.dateOfDeparture}</#if></#if>"
                   class="form-control small ${(dateOfDepartureError??)?string('is-invalid', '')}"
                   placeholder="Дата рождения"/>
            <#if dateOfDepartureError??>
                <div class="invalid-feedback">
                ${dateOfDepartureError}
                </div>
            </#if>
        </div>
    </div>
    </#if>



    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Исход :</label>
        <div class="col-sm-3">
            <select class="custom-select" name="opn" id="inputGroupSelect01">
                <#list opnList as obOpn>
                    <option value="${obOpn.id}">${obOpn.opn}</option>
                </#list>
            </select>
        </div>

        <label class="col-sm-2 col-form-label">Летальный исход :</label>
        <div class="col-sm-3">
            <input type="date" name="dateOfDeath"
                   value="<#if client??><#if client.dateOfDeath??>${client.dateOfDeath}</#if></#if>"
                   class="form-control small ${(dateOfDeathError??)?string('is-invalid', '')}"
                   placeholder="Дата исхода"/>
            <#if dateOfDeathError??>
                <div class="invalid-feedback">
                ${dateOfDeathError}
                </div>
            </#if>
        </div>
    </div>

    <#--<div class="form-group row">
        <label class="col-sm-2 col-form-label">Срок гестации :</label>
        <div class="col-sm-3">
            <input type="text" name="gestation" value="<#if client??><#if client.opn??>${client.opn}</#if></#if>"
                   class="form-control small ${(opnError??)?string('is-invalid', '')}"
                   placeholder="ОПН"/>
            <#if opnError??>
                <div class="invalid-feedback">
                ${opnError}
                </div>
            </#if>
        </div>

        <label class="col-sm-2 col-form-label">Вес :</label>
        <div class="col-sm-3">
            <input type="date" name="weightClient"
                   value="<#if client??><#if client.dateOfDeath??>${client.dateOfDeath}</#if></#if>"
                   class="form-control small ${(dateOfDeathError??)?string('is-invalid', '')}"
                   placeholder="Дата исхода"/>
            <#if dateOfDeathError??>
                <div class="invalid-feedback">
                ${dateOfDeathError}
                </div>
            </#if>
        </div>
    </div>-->

    <#if isSave>
        <div class="container">
            <div class="row justify-content-around">
                <div class="col-4">
                    <label class="col-form-label">Выбрать шкалу</label>
                </div>
                <div class="col-4">
                    <label class="col-form-label">Удалить шкалу</label>
                </div>
            </div>
            <div class="row justify-content-around">
                <div class="col-4">
                    <div class="input-group">
                        <select class="custom-select" name="selectedScheme" id="inputGroupSelect01">
                            <#if listForSelectScheme??>
                                <#list listForSelectScheme as selSchem>
                                    <option  value="${selSchem}">${selSchem}</option>
                                </#list>
                            </#if>
                        </select>
                        <div class="col-sm-1"><button type="submit" name="add_scheme"
                                                      value="add_scheme" class="btn btn-primary ml-0">Добавить</button></div>
                    </div>
                </div>
                <div class="col-4">
                    <div class="input-group">
                        <select class="custom-select" name="deleteScheme" id="inputGroupSelect01">
                            <#if listForDeleteScheme??>
                                <#list listForDeleteScheme as delSchem>
                                    <option  value="${delSchem}">${delSchem}</option>
                                </#list>
                            </#if>
                        </select>
                        <div class="col-sm-1"><button type="submit" name="delete_scheme"
                                                      value="delete_scheme" class="btn btn-primary ml-0">Удалить</button></div>

                    </div>
                </div>
            </div>
        </div>
    </#if>
    <#if listSchemeClient??>
        <table style="width: 50%" class="table-sm table-bordered mt-4 mb-4">
            <thead>
            <tr>
                <th scope="col">Название шкалы</th>
                <th scope="col">Количество балов</th>
                <th scope="col">Риск смрти</th>
            </tr>
            </thead>
            <tbody>
                <#list listSchemeClient as scheme>
                <tr>
                    <#list scheme as paramSchem>
                        <td>${paramSchem}</td>
                    </#list>
                </tr>
                </#list>
            </tbody>
        </table>
        <#if mortalityRisk??>
            <div class="container">
                <div class="row">
                    <div class="col-5 h4 ml-0">Риск летального исхода по методам</div>
                    <div class="col-2 h4">${mortalityRisk}%</div>
                </div>
            </div>
        </#if>
    </#if>

    <div class="form-group row">
        <#if isSave>
            <div class="col-sm-1 mr-4"><button type="submit" name="update_profile" value="update_profile" class="btn btn-primary ml-0 mt-4">Сохранить</button></div>
        <#else>
            <div class="col-sm-1"><button type="submit" class="btn btn-primary ml-0 mt-4">Создать профиль</button></div>
        </#if>
        <input type="hidden" value="${_csrf.token}" name="_csrf">
</form>


</div>
</#macro>