package com.iipsen2.app.daos.Upload;

import com.iipsen2.app.models.Upload;
import com.iipsen2.app.services.ProjectService;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UploadMapper implements ResultSetMapper<Upload>{
    public Upload map(final int index, final ResultSet r, final StatementContext ctx) throws SQLException {
        return new Upload(
                r.getLong("id") ,
                r.getString("filename"),
                r.getString("path"),
                r.getString("mime"),
                r.getString("extension"),
                ProjectService.getProject(r.getLong("project_id"))
        );
    }
}
