import axios from 'axios'

const apiUrlSlug = '/api/bikes'

async function get() {
   return await axios.get(apiUrlSlug).then((response) => response.data)
}

export default {
   get,
}
