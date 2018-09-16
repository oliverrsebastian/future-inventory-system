$({property: 0}).animate({property: 100}, {
        duration: 1000,
        step: function() {
          var _percent = Math.round(this.property);
          $("#progress").css("width",  _percent+"%");
        },
        complete: function() {
            $("#progress").addClass("done");
            $("#page-row").toggleClass("hide");
        }
      });

$(document).on("click",".nav-link", function () {
		var clickedBtnID = $(this).attr('id'); // or var clickedBtnID = this.id
		window.location.replace(clickedBtnID+".html");
});

