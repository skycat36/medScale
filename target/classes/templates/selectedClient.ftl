<#import "parts/common.ftl" as c>
<#import "parts/actionWithClient.ftl" as awc>
<#--<#include "parts/security.ftl">-->

<@c.page>
    <@awc.action "/client_list/select/${client.id}" "Работа с пользователем" true/>
</@c.page>