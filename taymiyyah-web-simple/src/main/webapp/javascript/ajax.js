// Full Text search
$(document).ready(function(){
  $('#nxt,#prv,#orgSrch,#trnsSrch').live('click', function() {
    $.ajax({ // ajax call starts
      url: 'searchthetext',
      type: "POST",
      data: 'ajax=yes&term=' + $('#term').val()
          +'&termHidden='+$('#termHidden').val()
          +'&locale='+$('#locale').val()
          +'&src='+$(this ).attr("id")
          +'&currentPage='+$('#currentPageHidden').val()
          +'&totalPages='+$('#totalPagesHidden').val()
          +'&original='+$('#originalHidden').val(),
//            dataType: 'json',
      success: function(data)
      {
        var resp = JSON.parse(data);
        var quranList = resp.quranList;
        var str ="";
        for(var i =0;i < quranList.length;i++)
        {
          var quran = quranList[i];
          var title = "("+quran.surahId+")"+quran.surahName +" "+quran.ayahId+". "+quran.juzName;
          str += "<div title=\""+title+"\" class=\"row well\" style=\"margin-right: 0px;margin-left: 0px;\">"
              +"<p style=\"font-size: xx-large\" dir=\"rtl\">"
              +quran.ayahText
              +" . ("+quran.surahName+" "+quran.ayahId+")"
              +"</p>";

          if(resp.lang == "en"){
            str +="<p style=\"font-size: large\" >"
                +quran.ayahTranslationText
                +"</p>";
          } else{
            if(resp.lang != "ar"){
              str +="<p style=\"font-size: large\" dir=\"rtl\">"
                  +quran.ayahTranslationText
                  +"</p>" ;
            }
          }

          str += "</div>";
        }
        $('#qtableDiv').html(str);
        $('#currentPage').html(resp.currentPage);
        $('#totalPages').html(resp.totalPages);
        $('#totalHits').html(resp.totalHits);
        $('#totalHitsSmall').html(resp.totalHits);
        $('#time').html(resp.time);
        $('#currentPageHidden').val(resp.currentPage);
        $('#totalPagesHidden').val(resp.totalPages);
        $('#originalHidden').val(resp.original);
        $('#termHidden').val(resp.term);
        $('#timeDiv' ).show();
        $('#qtableDiv' ).show();
        $('#paginationDiv' ).show();
      }
    });
    return false; // keeps the page from not refreshing
  });
});

//-- Id Search
$(document).ready(function(){
  $('#srch').live('click', function() {
    $.ajax({ // ajax call starts
      url: 'searchthetext',
      type: "POST",
      data: 'ajax=yes&radio=' + $('input:radio[name=radio]:checked').val()
          +'&surahId='+$('#ayahCombo').val()
          +'&ayahId='+$('#ayaNo').val()
          +'&locale='+$('#locale').val()
          +'&src='+$(this ).attr("id"),
//            dataType: 'json',
      success: function(data)
      {
        var resp = JSON.parse(data);
        var quranList = resp.quranList;
        var str ="";
        for(var i =0;i < quranList.length;i++)
        {
          var quran = quranList[i];
          var title = "("+quran.surahId+")"+quran.surahName +" "+quran.ayahId+". "+quran.juzName;
          str += "<div title=\""+title+"\" class=\"row well\" style=\"margin-right: 0px;margin-left: 0px;\">"
              +"<p style=\"font-size: xx-large\" dir=\"rtl\">"
              +quran.ayahText
              +" . ("+quran.surahName+" "+quran.ayahId+")"
              +"</p>";

          if(resp.lang == "en"){
            str +="<p style=\"font-size: large\" >"
                +quran.ayahTranslationText
                +"</p>";
          } else{
            if(resp.lang != "ar"){
              str +="<p style=\"font-size: large\" dir=\"rtl\">"
                  +quran.ayahTranslationText
                  +"</p>" ;
            }
          }

          str += "</div>";
        }

        $('#qtableDiv').html(str);
        $('#timeDiv' ).hide();
        $('#qtableDiv' ).show();
        $('#paginationDiv' ).hide();
      }
    });
    return false; // keeps the page from not refreshing
  });
});

