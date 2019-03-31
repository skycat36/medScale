<#import "../../parts/common.ftl" as c>
<#import "../../parts/actionWithOpn.ftl" as awc>

<@c.page>
    <@awc.action "/department/update/${idOpn}" "Обновить данные" true/>
</@c.page>