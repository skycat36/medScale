<#import "../../parts/common.ftl" as c>
<@c.page>
<script>
    function drawColClientInOPN() {
        // create pie chart
        var chart = anychart.pie();

        var dps = [[]];
        var yValue;
        var label;
        <#if listValue??>
            <#list listOpn as nameOpn>
                yValue = parseInt("${listValue[nameOpn_index]}");
                label = "${nameOpn}";
                dps[parseInt("0")].push({
                    x : label,
                    y : yValue
                });
            </#list>
        </#if>
        // set chart title
        chart.title('Количество клиентов в отделениях');

        // set chart data
        chart.data(dps[0]);

        // set container id for the chart
        chart.container('container0');

        // initiate chart drawing
        chart.draw();
    }

    function drawLetalInOPN() {
        // create data
        var dps = [[]];
        var yValue;
        var label;
        <#if listValue??>
            <#list listOpn as nameOpn>
                yValue = "${listValue[nameOpn_index]}";
                label = "${nameOpn}";
                dps[parseInt("0")].push({
                    x : label,
                    y : yValue
                });
            </#list>
        </#if>
        // set the chart type
        var chart = anychart.line();

        // create a series, set the data and name
        var series = chart.column(dps[0]);
        series.name("Количество");

        // enable and configure labels on the series
        series.labels(true);
        series.labels().format("{%value}");

        // set the chart title
        chart.title("Количество летальных исходов.");

        // set the titles of the axes
        chart.xAxis().title("Отделения");
        chart.yAxis().title("Количество пациентов");

        // set the container id
        chart.container("container0");

        // initiate drawing the chart
        chart.draw();
    }

    function drawTimeClientInOPN() {
        // create data
        var dps = [[]];
        var yValue;
        var label;
        <#if listValue??>
            <#list listOpn as nameOpn>
                yValue = "${listValue[nameOpn_index]}";
                label = "${nameOpn}";
                dps[parseInt("0")].push({
                    x : label,
                    y : yValue
                });
            </#list>
        </#if>
        // set the chart type
        var chart = anychart.line();

        // create a series, set the data and name
        var series = chart.column(dps[0]);
        series.name("Количество дней");

        // enable and configure labels on the series
        series.labels(true);
        series.labels().format("{%value}");

        // set the chart title
        chart.title("Средняя длительность пребывания больного на койке.");

        // set the titles of the axes
        chart.xAxis().title("Отделения");
        chart.yAxis().title("Количество дней");

        // set the container id
        chart.container("container0");

        // initiate drawing the chart
        chart.draw();
    }

    function drawLetalInYear() {
        // create a data table
        var table = anychart.data.table();
        table.addData([
            <#if listValue??>
                <#list listOpn as nameOpn>
                    <#if (nameOpn_index > 0)>,</#if>
                    ['${nameOpn}', ${listValue[nameOpn_index]}]

                </#list>
            </#if>
        ]);

        // map the data
        mapping = table.mapAs({value: 1});

        // create a stock chart
        var chart = anychart.stock();

        // create a plot and a line series
        series = chart.plot(0).line(mapping);

        series.name("Количество пациентов");

        // set the chart position and title
        chart.title("Статистика смертности по годам.");

        // set the chart container
        chart.container("container0");

        // initiate drawing the chart
        chart.draw();
    }

    function drawSeverityOnEachScalePcs(){
        <#if nameSchemes??>
            <#list nameSchemes as scheme>
                var dps = [[]];
                var yValue;
                var label;
                <#if listProc??>
                    <#list schemes[scheme_index] as sch>
                        yValue = "${sch}";
                        label = "${listProc[sch_index]}";
                        dps[parseInt("0")].push({
                            x : label,
                            y : yValue
                        });
                    </#list>
                </#if>

                // create line chart
                var chart = anychart.line();

                var series = chart.line(dps[0]);

                series.name("Количество пациентов");

                // enable and configure labels on the series
                //series.labels(true);
                //series.labels().format("{%value}");

                // set chart title
                chart.title("Степень тяжести по шкале " + String("${scheme}"));

                // set chart data
                //chart.data(dps[0]);

                //series.name("Количество пациентов");

                chart.xAxis().title("Проценты");
                chart.yAxis().title("Количество пациентов");

                // set container id for the chart
                chart.container('container' + ${scheme_index});

                // initiate chart drawing
                chart.draw();
            </#list>
        </#if>
    }

</script>
<form method="post" action="/chart_stat">
    <div class="text-left ml-5">
        <div class="input-group col-md-6">
            <div class="input-group mb-6">
                <div class="input-group-prepend">
                    <label class="input-group-text" for="inputGroupSelect01">Выбор графика</label>
                </div>
                <select class="custom-select" name="selectedField" id="inputGroupSelect01">
                    <option <#if selChart??><#if (selChart=="colClientInOPN")>selected</#if></#if>
                            value="colClientInOPN">Количество пациентов в отделении</option>
                    <option <#if selChart??><#if (selChart=="letalInOPN")>selected</#if></#if>
                            value="letalInOPN">%, смертности по отделениям</option>
                    <option <#if selChart??><#if (selChart=="timeClientInOPN")>selected</#if></#if>
                            value="timeClientInOPN">Средняя длительность прибывание пациента на койке</option>
                    <option <#if selChart??><#if (selChart=="letalInYear")>selected</#if></#if>
                            value="letalInYear">Смертность по годам</option>
                    <option <#if selChart??><#if (selChart=="severityOnEachScale")>selected</#if></#if>
                            value="severityOnEachScale">Степень тяжести по каждой шкале</option>
                </select>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="col-sm-1"><button type="submit" class="btn btn-primary ml-0">Выбрать</button></div>
            </div>
        </div>
    </div>
</form>
<div id="container0" style="height: 400px; width: 50%;"></div>
<div id="container1" style="height: 400px; width: 50%;"></div>
<div id="container2" style="height: 400px; width: 50%;"></div>
<div id="container3" style="height: 400px; width: 50%;"></div>
<div id="container4" style="height: 400px; width: 50%;"></div>
<div id="container5" style="height: 400px; width: 50%;"></div>
    <#if selChart??>
        <#if selChart == "colClientInOPN">
        <script>anychart.onDocumentReady(drawColClientInOPN())</script>
        </#if>

        <#if selChart == "letalInOPN">
        <script>anychart.onDocumentReady(drawLetalInOPN())</script>
        </#if>

        <#if selChart == "timeClientInOPN">
        <script>anychart.onDocumentReady(drawTimeClientInOPN())</script>
        </#if>

        <#if selChart == "letalInYear">
        <script>anychart.onDocumentReady(drawLetalInYear())</script>
        </#if>

        <#if selChart == "severityOnEachScale">
        <#--<script>-->
        <#--var schem = <#list ntiss as ob>Array([{${ob},])</#list>;-->
        <#--var proc = <#list listProc as pr>Array([{${pr},])</#list>;-->
        <#--anychart.onDocumentReady(drawSeverityOnEachScale('ntiss', proc, schem))-->
        <#--</script>-->

        <script>anychart.onDocumentReady(drawSeverityOnEachScalePcs())</script>

        <#--<script>-->
        <#--var schem = <#list trips as ob>Array([{${ob},])</#list>;-->
        <#--var proc = <#list listProc as pr>Array([{${pr},])</#list>;-->
        <#--anychart.onDocumentReady(drawSeverityOnEachScale('trips', proc, schem))-->
        <#--</script>-->

        <#--<script>-->
        <#--var schem = <#list sofa as ob>Array([{${ob},])</#list>;-->
        <#--var proc = <#list listProc as pr>Array([{${pr},])</#list>;-->
        <#--anychart.onDocumentReady(drawSeverityOnEachScale('sofa', proc, schem))-->
        <#--</script>-->

        <#--<script>-->
        <#--var schem = <#list crib2 as ob>Array([{${ob},])</#list>;-->
        <#--var proc = <#list listProc as pr>Array([{${pr},])</#list>;-->
        <#--anychart.onDocumentReady(drawSeverityOnEachScale('crib2', proc, schem))-->
        <#--</script>-->

        <#--<script>-->
        <#--var schem = <#list snappe as ob>Array([{${ob},])</#list>;-->
        <#--var proc = <#list listProc as pr>Array([{${pr},])</#list>;-->
        <#--anychart.onDocumentReady(drawSeverityOnEachScale('snappe', proc, schem))-->
        <#--</script>-->
        </#if>

    </#if>
</@c.page>