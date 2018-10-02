$({property: 0}).animate({property: 100}, {
        duration: 2500,
        step: function() {
          var _percent = Math.round(this.property);
          $("#progress").css("width",  _percent+"%");
        },
        complete: function() {
          alert("complete");
          $("#progress").addClass("done");
          $(document).on("click",".nav-link", function () {
				var clickedBtnID = $(this).attr('id'); // or var clickedBtnID = this.id
   				window.location.replace(clickedBtnID+".html");
			});
        }
      });

