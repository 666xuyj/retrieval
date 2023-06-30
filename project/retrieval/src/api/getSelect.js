import request from '@/utils/request'
function getSelect(){
  return  request({
    url:"http://localhost:8088/api/select",
    meth:"request"
  })  
}
export default getSelect