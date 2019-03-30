<#import "../../parts/common.ftl" as c>
<#import "../../parts/actionWithWorker.ftl" as aww>
<#--<#include "parts/security.ftl">-->

<@c.page>
    <@aww.action "/edit_profile_worker" "Обновить профиль" true/>
</@c.page>