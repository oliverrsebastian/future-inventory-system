$(document).on("click",".menu-item", function () {
		var clickedBtnID = $(this).attr('id'); // or var clickedBtnID = this.id
    $({property: 0}).animate({property: 100}, {
        duration: 1000,
        step: function() {
          var _percent = Math.round(this.property);
          $("#progress").css("width",  _percent+"%");
        },
        complete: function() {
            $("#progress").addClass("done");
            //alert(clickedBtnID);
            window.location.replace(clickedBtnID+".html");
        }
      });
});

$('.entryEditModal').on('show.bs.modal', function (event) {
    var button = $(event.relatedTarget) // Button that triggered the modal
    var dataName = button.data('name') // Extract info from data-* attributes
    // If necessary, you could initiate an AJAX request here (and then do the updating in a callback).
    // Update the modal's content. We'll use jQuery here, but you could use a data binding library or other methods instead.
    var modal = $(this)
    modal.find('.modal-title').text(dataName)
    // modal.find('.modal-body input').val(recipient)


    if (dataName.toUpperCase() == "EDIT ITEM") {
        // NOT WORKING
        $("#inputItemSku").prop("readonly", true);
    }
    // NOT WORKING
})