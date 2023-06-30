import request from '@/utils/request'

function getDownload(filename,keyword) {
  return  request({
    url:"http://localhost:8088/api/download",
    meth:"request",
    params:{
      fileName: filename,
      key:keyword
    }
  })
}

export default getDownload