import { apiRequest } from "../services/api";



/* =========================
   DELETE
========================= */


export const handleDelete = async ({
  endpoint,
  id,
  selectedItem,
  setSelectedItem,
  setList,
  setError,
  confirmMessage = "¿Está seguro de eliminar este registro?"
}) => {
  const confirmDelete = window.confirm(confirmMessage);
  if (!confirmDelete) return false;

  try {
    await apiRequest(`${endpoint}/${id}`, "DELETE");

    // Actualizar lista si existe
    if (setList) {
    //  setList(prev => prev.filter(item => item.id !== id));
      setList(prev => prev.filter(item => String(item.id) !== String(id)));

    }// Por esto (más seguro):

    // Cerrar detalle si corresponde
    if (setSelectedItem && selectedItem?.id === id) {
      setSelectedItem(null);
    return true;
    }
  } catch (err) {
    console.error(err);
    setError?.("Error al eliminar el registro");
  }
};

/* =========================
   UPDATE
========================= */
export const handleUpdate = async ({
  endpoint,
  id,
  updatedData,
  setList,
  setSelectedItem,
  setError
}) => {
  try {
    const updatedItem = await apiRequest(
      `${endpoint}/${id}`,
      "PUT",
      updatedData
    );

    // Actualizar lista
    if (setList) {
      setList(prev =>
        prev.map(item => (item.id === id ? updatedItem : item))
      );
    }

    // Actualizar detalle
    setSelectedItem?.(updatedItem);
  } catch (err) {
    console.error(err);
    setError?.("Error al actualizar el registro");
  }
};

/* =========================
   CREATE
========================= */
export const  handleCreate = async ({
  endpoint,
  newData,
  setList,
  setError
}) => {
  try {
    const createdItem = await apiRequest(endpoint, "POST", newData);

    if (setList) {
      setList(prev => [...prev, createdItem]);
    }
  } catch (err) {
    console.error(err);
    setError?.("Error al crear el registro");
  }
};

/* =========================
   FORMATEO HEADER DETALLE
========================= */
export const headersSelectedItem = (selectedItem) => {
  if (!selectedItem) return {};

  return {
    ...selectedItem,
    createdAt: selectedItem.createdAt
      ? new Date(selectedItem.createdAt).toLocaleString()
      : "-",
    updatedAt: selectedItem.updatedAt
      ? new Date(selectedItem.updatedAt).toLocaleString()
      : "-"
  };
};
// src/utils/filterUtils.js

/* ==========================================================================
   4. GET DISPLAYED DATA - Búsqueda Universal en tiempo real
   ========================================================================== */
/*
export const getDisplayedData = (data, searchTerm, fields = []) => {
  if (!data || data.length === 0) return [];
  if (!searchTerm || searchTerm.trim() === "") return data;

  const term = searchTerm.toLowerCase();

  return data.filter((item) => {
    // Solo busca en las llaves que tú le digas
    return fields.some((field) => {
      const value = item[field];
      return String(value).toLowerCase().includes(term);
    });
  });
};
/**
 * Filtra una lista basándose en campos específicos, soportando números y texto.
 */
export const getDisplayedData = (data, searchTerm, fields = []) => {
  if (!data || data.length === 0) return [];
  if (!searchTerm || searchTerm.trim() === "") return data;

  const term = searchTerm.toLowerCase();

  return data.filter((item) => {
    return fields.some((field) => {
      // 1. Obtenemos el valor (soporta anidados como "teacher.id")
      const value = field.split('.').reduce((obj, key) => obj?.[key], item);

      // 2. Si el valor es nulo o indefinido, no hay match
      if (value === null || value === undefined) return false;

      // 3. Convertimos el valor a String y buscamos el término
      // Esto hará que si buscas "12", lo encuentre en el ID 12 o 120
      return String(value).toLowerCase().includes(term);
    });
  });
};




