import axios from 'axios'

const apiUrlSlug = '/api/bikes/'

async function get() {
   return await axios
      .get(apiUrlSlug)
      .then((response) => response.data)
      .catch((error) => {
         console.error(error)
      })
}

async function post(newBikeTitle: string) {
   return await axios
      .post(apiUrlSlug, { title: newBikeTitle })
      .then((response) => response.data)
      .catch((error) => console.error(error))
}

export default {
   get,
   post,
}
