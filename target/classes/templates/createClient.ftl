<#import "parts/common.ftl" as c>
<#import "parts/actionWithClient.ftl" as awc>
<#--<#include "parts/security.ftl">-->

<@c.page>
    <@awc.action "/client_list/add_client" "Создать клиента" false/>
</@c.page>