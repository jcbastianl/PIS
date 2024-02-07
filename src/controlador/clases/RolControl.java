package controlador.clases;

import controlador.DAO.DaoImplement;
import controlador.TDA.listas.DynamicList;
import modelo.Rol;


public class RolControl extends DaoImplement<Rol>{
        private DynamicList<Rol> listR = new DynamicList<>();
        private Rol rol;

        public RolControl() {
                super(Rol.class);
        }

        public RolControl(Rol rol, Class<Rol> clazz) {
                super(clazz);
                this.rol = rol;
        }

        public DynamicList<Rol> getListR() {
                listR = all();
                return listR;
        }

        public void setListR(DynamicList<Rol> listR) {
                this.listR = listR;
        }

        public Rol getRol() {
                if (rol == null)
                rol = new Rol();
        return rol;
        }

        public void setRol(Rol rol) {
                this.rol = rol;
        }
        
        public Boolean persit(){
                rol.setId(all().getLenght());
                return persist(rol);
        }
        
        public static void main(String[] args) {
                RolControl rc = new RolControl();
                System.out.println(rc.all().toString());
//                rc.getRol().setNombre("Docente");
//                rc.getRol().setDescripcion("Una persona con un rol de docente");
//                rc.persit();
//                rc.setRol(null);
//                rc.getRol().setNombre("Estudiante");
//                rc.getRol().setDescripcion("persona con rol de estudiante, que recibe clases");
//                rc.persit();
//                rc.setRol(null);
//                rc.getRol().setNombre("Admin");
//                rc.getRol().setDescripcion("Usuario con acceso de manipulacion de los datos guardados");
//                rc.persit();
//                rc.setRol(null);        
        }
}