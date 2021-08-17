<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="form-row">
	<div class="form-group col-md-6">
		<label for="cargoDescription">Description of cargo</label>
		<textarea class="form-control --flight-reservation-control"
			id="cargoDescription" name="cargoDescription"
			placeholder="Please enter your description here..." rows="3"></textarea>
	</div>
</div>
<div class="form-row">
	<div class="form-group col-md-6">
		<label for="customFileLabel">Specification file</label>
		<div class="custom-file">
			<label class="custom-file-label " for="specificationFile"
				id="customFileLabel">Example: specification.docs</label> <input
				type="file" class="custom-file-input --flight-reservation-control"
				id="specificationFile" name="specificationFile">
		</div>
	</div>
</div>