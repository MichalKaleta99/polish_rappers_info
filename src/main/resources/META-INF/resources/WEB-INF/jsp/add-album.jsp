<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>


<div class="container">
  <h1> Dodaj album dla ${askedRapper.name} </h1>
  <form:form method="post" modelAttribute="album">
  <fieldset class="mb-3">
        Nazwa albumu
        <form:input type="text" path="name" required="required"/>
        <form:errors path="name" cssClass="text-warning"/>
      </fieldset>
    <fieldset class="mb-3">
      Długość albumu (xx:yy:zz)
      <form:input type="text" path="lengthOfAlbum" pattern="\d\d:\d\d:\d\d?" title="Proszę podać długość w formacie xx:yy:zz lub pozostawić je puste" />
    </fieldset>

    <fieldset class="mb-3">
      <form:label path="numberOfSongs">Liczba utworów</form:label>
      <form:input type="text" path="numberOfSongs" value=""/>
    </fieldset>

    <form:input type="hidden" path="id"/>
    <input type="submit" a href="album" class="btn btn-success"></a>
  </form:form>
</div>

<script>
document.addEventListener('DOMContentLoaded', function() {
  document.getElementById('numberOfSongs').value = '';
});

</script>

<%@ include file="common/footer.jspf" %>


