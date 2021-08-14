$(document).ready(function () {
    $('#passenger').change(function () {
        $('#transport').prop('checked', false);
        $('#transport').prop('disabled', $(this).prop('checked'));
    })
    $('#transport').change(function () {
        $('#passenger').prop('checked', false);
        $('#passenger').prop('disabled', $(this).prop('checked'));
    })
});