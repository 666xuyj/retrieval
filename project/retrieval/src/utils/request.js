import axios from 'axios'
const service = axios.create({
  baseURL:process.env.BASE_API,
  
})
//拦截器，不需要token就直接返回config了
service.interceptors.request.use(
  config => {
      return config
  },
  error => {
      console.log(error) 
      return Promise.reject(error)
  }
)

//response响应拦截
axios.interceptors.response.use(response => {
  let res = response.data;
  // console.log(res)

  if (res.code === 200) {
      return response
  } else {
      return Promise.reject(response.data.msg)
  }
},
  error => {
      console.log(error)
      if (error.response.data) {
          error.message = error.response.data.msg
      }
      return Promise.reject(error)
  }
)


export default service
