document.addEventListener('DOMContentLoaded', function () {
    var crearPlanLink = document.getElementById('crearPlanLink');
    var crearPlanForm = document.getElementById('crearPlanForm');

    crearPlanLink.addEventListener('click', function (event) {
        event.preventDefault(); // Evitar que el enlace cambie la ubicación

        if (crearPlanForm.style.display === 'none' || crearPlanForm.style.display === '') {
            crearPlanForm.style.display = 'block';
        } else {
            crearPlanForm.style.display = 'none';
        }
    });
});
