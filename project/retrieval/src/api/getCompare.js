import axios from 'axios'

function getCompare(keyword) {
  const url = 'http://localhost:8088/api/cmp'
  const params = {
    param: keyword
  }
  return axios.get(url, { params })
}

export default getCompare