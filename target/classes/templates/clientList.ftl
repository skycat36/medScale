<#import "parts/common.ftl" as c>
<#import "parts/actionWith.ftl" as aw>

<@c.page>
<div class="text-center">
    <h1 class="mb-3">База данных клиентов</h1>
</div>
    <@aw.actionWith "/client_list" true/>
</@c.page>