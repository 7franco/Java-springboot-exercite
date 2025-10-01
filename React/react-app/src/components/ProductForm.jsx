import { useEffect, useState } from "react";

const initialDataForm = {
  id: 0,
  name: '',
  description: '',
  price: ''
}

export const ProductForm = ({ productSelected, handlerAdd }) => {
  const [form, setForm] = useState(initialDataForm);

  const {id, name, description, price } = form;

  const handleChange = (event) => {
    const { name, value } = event.target;
    setForm({
      ...form,        
      [name]: value   
    });
  }; 

  useEffect(() =>{
    setForm(productSelected)
  }, [productSelected])

  return (
    <form onSubmit={(event) => {
        event.preventDefault();
        if(!name || !description || !price){
            alert('Debe completar los datos del formulario');
        }
        handlerAdd(form)
        setForm(initialDataForm);
    }}>
      <div>
        <input
          placeholder="Name"
          className="form-control my-3 w-75"
          name="name"
          value={name}
          onChange={handleChange}
        />
      </div>
      <div>
        <input
          placeholder="Description"
          className="form-control my-3 w-75"
          name="description"
          value={description}
          onChange={handleChange}
        />
      </div>
      <div>
        <input
          placeholder="Price"
          className="form-control my-3 w-75"
          name="price"
          value={price}
          onChange={handleChange}
        />
      </div>
      <button type="submit" className="btn btn-primary">
        {id >0  ? 'Update':'Create'}
        </button>
    </form>
  )
}
