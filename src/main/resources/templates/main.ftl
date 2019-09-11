<#import "macros/common.ftl" as c>

<@c.page>
<div class="form-row">
    <div class="form-group col-md-6">
        <form method="get" action="/main" class="form-inline">
            <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by tag">
            <button type="submit" class="btn btn-primary ml-2">Search</button>
        </form>
    </div>
</div>

<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
    Add new Message
</a>
<div class="collapse <#if speciality??>show</#if>" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" class="form-control ${(descriptionError??)?string('is-invalid', '')}"
                       value="<#if speciality??>${speciality.description}</#if>" name="description" placeholder="Введите описание" />
                <#if descriptionError??>
                    <div class="invalid-feedback">
                    ${descriptionError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" class="form-control"
                       value="<#if speciality??>${speciality.specialityName}</#if>" name="specialityName" placeholder="Название специальности">
                <#if specialityNameError??>
                    <div class="invalid-feedback">
                    ${specialityNameError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Choose file</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Добавить</button>
            </div>
        </form>
    </div>
</div>

<div class="card-columns">
    <#list messages as speciality>
        <div class="card my-3">
            <#if speciality.filename??>
                <img src="/img/${speciality.filename}" class="card-img-top">
            </#if>
            <div class="m-2">
                <span>${speciality.description}</span>
                <i>${speciality.specialityName}</i>
            </div>
            <div class="card-footer text-muted">
            ${speciality.authorName}
            </div>
        </div>
    <#else>
        No speciality
    </#list>
</div>
</@c.page>
