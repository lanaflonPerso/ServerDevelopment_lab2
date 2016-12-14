/**
 * Created by o_0 on 2016-12-14.
 */
var restpipeaddr = "http://localhost:3500/";
var socketRestPipe = io(restpipeaddr);

app.service('socketService',function () {
    this.getRestPipeSocket = function () {
        return socketRestPipe;
    }
})