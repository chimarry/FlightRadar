$(document).ready(function () {
    $('#user').change(function () {
        $('#employee').prop('checked', false);
        $('#employee').prop('disabled', $(this).prop('checked'));
    })
    $('#employee').change(function () {
        $('#user').prop('checked', false);
        $('#user').prop('disabled', $(this).prop('checked'));
    })
});