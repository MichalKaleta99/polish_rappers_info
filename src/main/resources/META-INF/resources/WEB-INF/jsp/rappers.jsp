<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<link href="css/search.css" rel="stylesheet"/>
<script>

function formatNumber(number) {
    if (number >= 1000000) {
      return (number / 1000000).toLocaleString() + 'M';
    } else if (number >= 1000) {
      return (number / 1000).toLocaleString() + 'K';
    } else {
      return number.toLocaleString();
    }
  }

</script>



<div class="container">
    <h1> Lista raperów </h1>
    <table class="table">
    <thead>
    <tr>
    <th>ID</th>
    <th>Ksywka</th>
    <th>Data urodzenia</th>
    <th>Instagram</th>
    <th>Liczba obserwujących</th>
    <th>Lista albumów </th>
    <th></th>
    <th></th>


    </tr>

    </thead>

    <tbody>
    <c:forEach items="${rappers}" var="rapper">
    <tr>
    <td>${rapper.rapper.id}</td>
    <td>${rapper.rapper.name}</td>
    <td>${rapper.rapper.dateOfBirth}</td>
    <td>${rapper.name}</td>


<td>
  <c:choose>
    <c:when test="${empty rapper.name && rapper.followers == 0}">
      <c:out value="" />
          </c:when>
    <c:otherwise>
      <script>
        document.write(formatNumber(${rapper.followers}));
      </script>
    </c:otherwise>
  </c:choose>
</td>




    <td><a href="album-list?id=${rapper.rapper.id}" class="btn btn-light"> A </td>
    <td><a href="delete-rapper?id=${rapper.rapper.id}" class="btn btn-danger"> X </td>
    <td><a href="edit-rapper?id=${rapper.rapper.id}" class="btn btn-dark"> E </td>

    </tr>
    </c:forEach>
    </tbody>
    </table>
    <a href="add-rapper" class="btn btn-success"> Dodaj rapera</a>
</div>


    <%@ include file="common/footer.jspf" %>

