import axios from 'axios'

function getDownload(filename) {
  const url = 'http://localhost:8088/api/download'
  const params = {
    fileName: filename
  }
  return axios.request(url, { params })
}

export default getDownload