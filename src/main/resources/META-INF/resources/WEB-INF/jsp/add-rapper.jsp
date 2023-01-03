<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<div class="container">
  <h1> Dodaj nowego rapera </h1>
  <form:form method="post" modelAttribute="ig_account">
    <fieldset class="mb-3">
      Ksywka
      <form:input type="text" path="rapper.name" required="required"/>
      <form:errors path="name" cssClass="text-warning"/>
    </fieldset>

    <fieldset class="mb-3">
      <form:label path="rapper.dateOfBirth">Data urodzenia (nieobowiązkowo)</form:label>
      <form:input type="date" path="rapper.dateOfBirth"/>
    </fieldset>

    <fieldset class="mb-3">
      Nazwa konta Instagram:
      <form:input type="text" path="name"/>
    </fieldset>

    <fieldset class="mb-3">
      Liczba followersów na Instagramie:
      <form:input type="text" path="followers"/>
    </fieldset>

    <form:input type="hidden" path="id"/>
    <input type="submit" a href="ig_account" class="btn btn-success"></a>
  </form:form>
</div>

<%@ include file="common/footer.jspf" %>

<script type="text/javascript">
  document.querySelector('form').addEventListener('submit', function(event) {
    event.preventDefault();  // zablokuj domyślne wysłanie formularza

    // pobierz wartości pól "name" i "followers"
    const name = document.querySelector('input[name="name"]').value;
    const followers = document.querySelector('input[name="followers"]').value;

    if (followers && !name) {
      // wyświetl komunikat o błędzie i zablokuj wysłanie formularza
      alert('Nazwa konta Instagram jest wymagana, jeśli podajesz liczbę followersów.');
      return;
    }

    // jeśli oba pola są wypełnione, wyślij formularz
    this.submit();
  });
</script>
