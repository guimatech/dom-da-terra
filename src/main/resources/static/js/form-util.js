const startDateFields = document.getElementsByClassName("startDate");
const endDateFields = document.getElementsByClassName("endDate");

const setMinDate = function(element) {
    const now = new Date();
    now.setHours(now.getHours() - 3);

    if (now.getHours() > 21) {
        now.setDate(now.getDate() - 1);
    }

    element.min = now.toISOString().split("T")[0];
}

const setEndDateMinAndValue = function(startDateField, endDateField) {
  endDateField.min = startDateField.value;
  if (endDateField.value < startDateField.value) {
    endDateField.value = startDateField.value;
  }
}

// Para cada elemento com a classe "startDate", set a data mínima e adicione um ouvinte para alterar o valor mínimo e valor da data de fim
for (let i = 0; i < startDateFields.length; i++) {
  const startDateField = startDateFields[i];
  setMinDate(startDateField);

  const endDateField = endDateFields[i];
  setEndDateMinAndValue(startDateField, endDateField);

  startDateField.onchange = function () {
    setEndDateMinAndValue(startDateField, endDateField);
  }
}

function isNumberKey(evt) {
	var charCode = (evt.which) ? evt.which : evt.keyCode;

	if ((charCode >= 48 && charCode <= 57) || charCode <= 31) {
		return true;
	}

	return false;
}

(function () {
    'use strict'

    var forms = document.querySelectorAll('.needs-validation')

    Array.prototype.slice.call(forms)
    .forEach(function (form) {
        form.addEventListener('submit', function (event) {
        if (!form.checkValidity()) {
            event.preventDefault()
            event.stopPropagation()
        }

        form.classList.add('was-validated')
        }, false)
    })
})()