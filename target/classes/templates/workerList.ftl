<#import "parts/common.ftl" as c>
<#import "parts/actionWith.ftl" as aw>

<@c.page>
        <div class="text-center">
            <h1 class="mb-3">База данных работников</h1>
        </div>
    <@aw.actionWith "/worker_list" false/>
</@c.page>