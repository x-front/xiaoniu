/**
 * Created by Administrator on 2016/8/15.
 */
window.onload=function(){
  var oVideo=document.getElementById("pro-video");
  var oBtn=document.getElementById("pro-p-btn");
    oV(oVideo,oBtn);
  function oV(obj,btn){
      btn.onclick=function(){
          if(obj.paused){
              obj.play();
          }else{
              obj.pause();
          }
      }
  }
}