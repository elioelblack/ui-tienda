function goBack() {
    history.go(-1);
}
function redirectTo(url) {
    window.location.href = url;
}
function handleSubmit(args, dialog) {
    if (args !== null && args.validationFailed) {
        PF(dialog).jq.effect("shake", {times: 5}, 100);
    } else {
        PF(dialog).hide();
    }
}
function handleSubmit(args, dialog, reload) {
    if (args !== null && args.validationFailed) {
        PF(dialog).jq.effect("shake", {times: 5}, 100);
    } else {
        PF(dialog).hide();
        if (reload) {
            this.refreshPage();
        }
    }
}
function handleRefreshPage(args, componentWidgetVar) {
    if (args !== null && args.validationFailed) {
        PF(componentWidgetVar).jq.effect("explode", {times: 5}, 100);
    } else {
        this.refreshPage();
    }
}
function refreshPage() {
    window.location.reload();
}
