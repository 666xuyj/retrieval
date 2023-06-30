import request from '@/utils/request'

function getPath(path) {
  return  request({
    url:"http://localhost:8088/api/path",
    meth:"get",
    params:{
      param: path
    }
  })
}

export default getPath