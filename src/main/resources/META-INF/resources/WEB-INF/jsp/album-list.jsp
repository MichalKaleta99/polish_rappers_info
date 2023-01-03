<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<link href="css/search.css" rel="stylesheet"/>
<script>

</script>



<div class="container">
    <h1> Raper ${askedRapper.name} posiada ${numberOfAlbums} albumów </h1>
    <h1> Oto ich lista: </h1>
    <table class="table">
    <thead>
    <tr>

    <th>Nazwa albumu</th>
    <th>Ilość utworów</th>
    <th>Długość</th>
    <th></th>

    </tr>

    </thead>

    <tbody>
    <c:forEach items="${albumList}" var="album">
    <tr>

    <td>${album.name}</td>
    <td>
      <c:choose>
        <c:when test="${album.numberOfSongs==0}">
          <c:out value="" />
              </c:when>
        <c:otherwise>
          ${album.numberOfSongs}
        </c:otherwise>
      </c:choose>
    </td>
    <td>${album.lengthOfAlbum}</td>
     <td><a href="delete-album?rapperId=${askedRapper.id}&albumId=${album.id}" class="btn btn-danger"> X </td>


    </tr>
    </c:forEach>
    </tbody>
    </table>
    <a href="add-album?id=${askedRapper.id}" class="btn btn-success"> Dodaj album dla tego rapera</a>
</div>


    <%@ include file="common/footer.jspf" %>

